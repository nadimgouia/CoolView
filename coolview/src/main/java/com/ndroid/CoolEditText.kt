package com.ndroid

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.util.Log


/**
 * Created by Nadim on 29/09/2017.
 */

@Suppress("unused", "DEPRECATION")
class CoolEditText : android.support.v7.widget.AppCompatEditText {

    internal var borderRadius = 0f
    internal var borderStroke = 0
    internal var borderColor = 0
    internal lateinit var shape: GradientDrawable

    internal var colorId: Int = 0

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        val a = context.obtainStyledAttributes(attrs,
                R.styleable.Options, 0, 0)

        // Border attributes...
        borderRadius = a.getFloat(R.styleable.Options_border_radius, 0f)
        borderStroke = a.getInt(R.styleable.Options_border_stroke, 0)
        borderColor = a.getInt(R.styleable.Options_border_color, 0)

        shape = GradientDrawable()

        try {
            val buttonColor = background as ColorDrawable
            colorId = buttonColor.color
            shape.setColor(colorId)
        } catch (ex: Exception) {
            Log.e("parsing error", ex.message)
        }

        a.recycle()

        initView()
        setPadding(20, 20, 20, 20)

    }

    constructor(context: Context) : super(context) {}

    fun initView() {

        shape.cornerRadius = borderRadius
        shape.setStroke(borderStroke, borderColor)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            background = shape
        } else {
            setBackgroundDrawable(shape)
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
}
