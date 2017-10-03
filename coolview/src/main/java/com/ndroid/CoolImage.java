package com.ndroid;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by Nadim on 02/10/2017.
 */

public class CoolImage extends android.support.v7.widget.AppCompatImageView {

    float borderRadius;
    int borderStroke, borderColor;
    Drawable src;
    Bitmap bitmap, originalBitmap;
    Boolean isCircle = false;

    public CoolImage(Context context) {
        super(context, null);
    }

    public CoolImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.Options, 0, 0);

        borderRadius = a.getFloat(R.styleable.Options_border_radius, 0f);
        borderStroke = a.getInt(R.styleable.Options_border_stroke, 0);
        borderColor = a.getInt(R.styleable.Options_border_color, 0);
        isCircle = a.getBoolean(R.styleable.Options_is_circle, false);
        src = getDrawable();

        a.recycle();

        originalBitmap = drawableToBitmap(src);
        bitmap = drawableToBitmap(src);

        initView();
    }

    private void initView() {

        if (isCircle) {
            bitmap = getCircleBitmap(originalBitmap);
        } else {
            bitmap = getRectangleBitmap(originalBitmap);
        }

        setScaleType(ScaleType.FIT_XY);
        setImageBitmap(bitmap);

    }

    private Bitmap getRectangleBitmap(Bitmap mbitmap) {

        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(borderRadius * 2);
        shape.setStroke(borderStroke, borderColor);

        Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
        Canvas canvas = new Canvas(imageRounded);
        Paint mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), borderRadius * 5, borderRadius * 5, mpaint);

        setPadding(borderStroke, borderStroke, borderStroke, borderStroke);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(shape);
        } else {
            setBackgroundDrawable(shape);
        }

        return imageRounded;
    }

    private Bitmap getCircleBitmap(Bitmap mbitmap) {

        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(mbitmap.getWidth() / 2);
        shape.setStroke(borderStroke, borderColor);

        Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
        Canvas canvas = new Canvas(imageRounded);
        Paint mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), mbitmap.getWidth() / 2, mbitmap.getHeight() / 2, mpaint);

        setPadding(borderStroke, borderStroke, borderStroke, borderStroke);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(shape);
        } else {
            setBackgroundDrawable(shape);
        }

        return imageRounded;

    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
            // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (isCircle) {
            int width = getLayoutParams().width;
            int height = getLayoutParams().height;

            if (width < height) {
                getLayoutParams().width = width;
                getLayoutParams().height = width;
            } else {
                getLayoutParams().width = height;
                getLayoutParams().height = height;
            }
        }
    }


    public float getBorderRadius() {
        return borderRadius;
    }

    public void setBorderRadius(float borderRadius) {
        this.borderRadius = borderRadius;
        initView();
    }

    public int getBorderStroke() {
        return borderStroke;
    }

    public void setBorderStroke(int borderStroke) {
        this.borderStroke = borderStroke;
        initView();
    }

    public int getBorderColor() {
        return borderColor;
    }


    public void setBorderColor(@ColorInt int borderColor) {
        this.borderColor = borderColor;
        initView();
    }


    public void setBorderColor(String borderColor) {
        this.borderColor = Color.parseColor(borderColor);
        initView();
    }

    public Boolean getCircle() {
        return isCircle;
    }

    public void setCircle(Boolean circle) {
        isCircle = circle;
        initView();
    }
}
