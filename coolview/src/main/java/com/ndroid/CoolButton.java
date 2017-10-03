package com.ndroid;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.util.Log;


/**
 * Created by Nadim on 28/09/2017.
 */

public class CoolButton extends android.support.v7.widget.AppCompatButton {

    float borderRadius = 0;
    int borderStroke = 0;
    int borderColor = 0;
    GradientDrawable shape;

    int colorId;

    public CoolButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.Options, 0, 0);

        // Border attributes...
        borderRadius = a.getFloat(R.styleable.Options_border_radius, 0f);
        borderStroke = a.getInt(R.styleable.Options_border_stroke, 0);
        borderColor = a.getInt(R.styleable.Options_border_color, 0);

        shape = new GradientDrawable();

        try {
            ColorDrawable buttonColor = (ColorDrawable) getBackground();
            colorId = buttonColor.getColor();
            shape.setColor(colorId);
        } catch (Exception ex) {
            shape.setColor(Color.parseColor("#cecfcf"));
            Log.e("parsing error", ex.getMessage());
        }

        a.recycle();

        initView();

        setPadding(20, 20, 20, 20);

    }

    public CoolButton(Context context) {
        this(context, null);
    }


    public void initView() {

        shape.setCornerRadius(borderRadius);
        shape.setStroke(borderStroke, borderColor);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(shape);
        } else {
            setBackgroundDrawable(shape);
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


}
