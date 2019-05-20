package com.ndroid

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.support.annotation.ColorInt
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by Nadim on 02/10/2017.
 */

@Suppress("unused", "DEPRECATION")
class CoolImage : AppCompatImageView {

    internal var borderRadius: Float = 0.toFloat()
    internal var borderStroke: Int = 0
    internal var borderColor: Int = 0
    internal lateinit var src: Drawable
    internal lateinit var bitmap: Bitmap
    internal lateinit var originalBitmap: Bitmap
    internal var isCircle: Boolean = false

    var circle: Boolean
        get() = isCircle
        set(circle) {
            isCircle = circle
            initView()
        }

    constructor(context: Context) : super(context, null)

    @SuppressLint("CustomViewStyleable")
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {

        val a = context.obtainStyledAttributes(attrs,
                R.styleable.Options, 0, 0)

        borderRadius = a.getFloat(R.styleable.Options_border_radius, 0f)
        borderStroke = a.getInt(R.styleable.Options_border_stroke, 0)
        borderColor = a.getInt(R.styleable.Options_border_color, 0)
        isCircle = a.getBoolean(R.styleable.Options_is_circle, false)
        src = drawable

        a.recycle()

        originalBitmap = drawableToBitmap(src)
        bitmap = drawableToBitmap(src)

        initView()
    }

    private fun initView() {

        bitmap = if (isCircle) {
            getCircleBitmap(originalBitmap)
        } else {
            getRectangleBitmap(originalBitmap)
        }

        scaleType = ImageView.ScaleType.FIT_XY
        setImageBitmap(bitmap)

    }

    private fun getRectangleBitmap(mbitmap: Bitmap): Bitmap {

        val shape = GradientDrawable()
        shape.cornerRadius = borderRadius * 2
        shape.setStroke(borderStroke, borderColor)

        val imageRounded = Bitmap.createBitmap(mbitmap.width, mbitmap.height, mbitmap.config)
        val canvas = Canvas(imageRounded)
        val mpaint = Paint()
        mpaint.isAntiAlias = true
        mpaint.shader = BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        canvas.drawRoundRect(RectF(0f, 0f, mbitmap.width.toFloat(), mbitmap.height.toFloat()), borderRadius * 5, borderRadius * 5, mpaint)

        setPadding(borderStroke, borderStroke, borderStroke, borderStroke)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            background = shape
        } else {
            setBackgroundDrawable(shape)
        }

        return imageRounded
    }

    private fun getCircleBitmap(mbitmap: Bitmap): Bitmap {

        val shape = GradientDrawable()
        shape.cornerRadius = (mbitmap.width / 2).toFloat()
        shape.setStroke(borderStroke, borderColor)

        val imageRounded = Bitmap.createBitmap(mbitmap.width, mbitmap.height, mbitmap.config)
        val canvas = Canvas(imageRounded)
        val mpaint = Paint()
        mpaint.isAntiAlias = true
        mpaint.shader = BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        canvas.drawRoundRect(RectF(0f, 0f, mbitmap.width.toFloat(), mbitmap.height.toFloat()), (mbitmap.width / 2).toFloat(), (mbitmap.height / 2).toFloat(), mpaint)

        setPadding(borderStroke, borderStroke, borderStroke, borderStroke)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            background = shape
        } else {
            setBackgroundDrawable(shape)
        }

        return imageRounded

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        if (isCircle) {
            val width = layoutParams.width
            val height = layoutParams.height

            if (width < height) {
                layoutParams.width = width
                layoutParams.height = width
            } else {
                layoutParams.width = height
                layoutParams.height = height
            }
        }
    }


    fun getBorderRadius(): Float {
        return borderRadius
    }

    fun setBorderRadius(borderRadius: Float) {
        this.borderRadius = borderRadius
        initView()
    }

    fun getBorderStroke(): Int {
        return borderStroke
    }

    fun setBorderStroke(borderStroke: Int) {
        this.borderStroke = borderStroke
        initView()
    }

    fun getBorderColor(): Int {
        return borderColor
    }


    fun setBorderColor(@ColorInt borderColor: Int) {
        this.borderColor = borderColor
        initView()
    }


    fun setBorderColor(borderColor: String) {
        this.borderColor = Color.parseColor(borderColor)
        initView()
    }

    companion object {

        fun drawableToBitmap(drawable: Drawable): Bitmap {
            var bitmap: Bitmap?

            if (drawable is BitmapDrawable) {
                if (drawable.bitmap != null) {
                    return drawable.bitmap
                }
            }

            bitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
                Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
                // Single color bitmap will be created of 1x1 pixel
            } else {
                Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
            }

            val canvas = Canvas(bitmap!!)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            return bitmap
        }
    }
}
