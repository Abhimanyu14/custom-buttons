package com.appz.abhi.simplebuttons

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.Button

class SimpleButton : Button {


    private var buttonWidth = 0
    private var buttonHeight = 0
    private var buttonCornerRadius = 0F

    private var buttonTextColor = 0
    private var buttonBackgroundColor = 0
    private var buttonPressedColor = 0
    private var buttonBorderColor = 0

    private var buttonType = 0
    private var buttonShape = 0

    private lateinit var gradientDrawable: GradientDrawable

    private var drawnFlag = false


    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }


    private fun init(context: Context, attrs: AttributeSet) {

        if (isInEditMode) {
            return
        }

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SimpleButton)

        buttonTextColor = typedArray.getColor(
            R.styleable.SimpleButton_button_text_color,
            resources.getColor(R.color.default_text_color)
        )

        buttonBackgroundColor = typedArray.getColor(
            R.styleable.SimpleButton_button_background_color,
            resources.getColor(R.color.default_background_color)
        )

        buttonPressedColor = typedArray.getColor(
            R.styleable.SimpleButton_button_pressed_color,
            resources.getColor(R.color.default_pressed_color)
        )

        buttonBorderColor = typedArray.getColor(
            R.styleable.SimpleButton_button_border_color,
            resources.getColor(R.color.default_border_color)
        )

        buttonType = typedArray.getInt(R.styleable.SimpleButton_button_type, 0)
        buttonShape = typedArray.getInt(R.styleable.SimpleButton_button_shape, 0)

        typedArray.recycle()

        /*
        roundRadius = typedArray.getDimensionPixelSize(
            R.styleable.CustomButton_round_radius,
            resources.getDimensionPixelSize(R.dimen.round_radius)
        )
        */

        this.isClickable = true
        this.isAllCaps = false

        this.setTextColor(buttonTextColor)
        this.setBackgroundResource(R.drawable.rectangle)
        gradientDrawable = this.background as GradientDrawable

        updateButton(false)
    }


    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)

        Log.e("Library", "On size changed")

        buttonWidth = width
        buttonHeight = height
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (drawnFlag) {
            return
        }

        Log.e("Library", "On draw")

        when (buttonShape) {

            // Rectangle
            0 -> {
                buttonCornerRadius = 0F
            }

            // Curved
            1 -> {
                buttonCornerRadius = buttonHeight.toFloat() / 2
            }

            // Fillet
            2 -> {
                buttonCornerRadius = 8F
            }
        }

        gradientDrawable.cornerRadius = buttonCornerRadius

        drawnFlag = true
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.action) {

            MotionEvent.ACTION_DOWN -> {
                Log.e("Library", "On down")

                updateButton(true)
            }

            MotionEvent.ACTION_UP -> {
                Log.e("Library", "On up")

                updateButton(false)
            }
        }

        return super.onTouchEvent(event)
    }


    private fun updateButton(pressed: Boolean) {

        when (buttonType) {

            // Solid
            0 -> {

                if (pressed) {
                    gradientDrawable.setColor(buttonPressedColor)

                } else {
                    gradientDrawable.setColor(buttonBackgroundColor)
                }
            }

            // Outline
            1 -> {

                gradientDrawable.setColor(resources.getColor(R.color.transparent))

                if (pressed) {
                    gradientDrawable.setStroke(3, buttonPressedColor)

                } else {
                    gradientDrawable.setStroke(3, buttonBackgroundColor)
                }
            }
        }
    }
}
