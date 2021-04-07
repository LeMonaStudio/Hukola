package com.nativecitizens.hukola.util

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.nativecitizens.hukola.R


class SpeedView @JvmOverloads constructor(
    context: Context, attr: AttributeSet?, defStyleAttr: Int = 0): View(context, attr, defStyleAttr){

    /**
     * All the default values that will be used for the SpeedView in absence of user-defined XML attributes
     */

    private var min = 0
    private var max =  200
    private var currentSpeed = 0f

    //default drawColor for both the speed indicator and its background
    private var drawColor = Color.DKGRAY

    //Paint for the speed indicator and its background
    private var paintSpeedIndicator = Paint(Paint.ANTI_ALIAS_FLAG)
    private var paintSpeedBg = Paint(Paint.ANTI_ALIAS_FLAG)
/*
    //textSize for the speed value converted to pixels
    private var speedTextSizeInPX = spToPX(24, context)
    //the default speed label text size
    private var speedLabelTextSizeInPx = spToPX(16, context)*/

    //the default strokeWidths
    private var strokeWidthSpeedBgInPX = dpToPX(4, context)
    //the default currentSpeed stroke width
    private var strokeWidthSpeedIndicatorInPX = dpToPX(4, context)

    //start angle for the speed indicator arc
    //starting clock-wisely where by default 0 is 3, 90 is 6, 180 is on 9 and 270 on 12, and 360 back to 3
    private val startAngle = -90f

    //rectF to store coordinates for drawing the speed indicator arc
    private var rectFSpeedIndicator = RectF()
    //rectF to store coordinates for drawing the speed background oval
    private var rectFSpeedBg = RectF()

    //calling initialization
    init {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.SpeedView)

        if(attr != null){
            //if there are user-defined attr in the XML, we extract it
            try {
                min = typedArray.getInt(R.styleable.SpeedView_min, min)
                max = typedArray.getInt(R.styleable.SpeedView_max, max)
                currentSpeed = typedArray.getFloat(R.styleable.SpeedView_currentSpeed, currentSpeed)

                drawColor = typedArray.getInt(R.styleable.SpeedView_drawColor, drawColor)

                strokeWidthSpeedBgInPX = typedArray.getDimension(R.styleable.SpeedView_speedBackgroundStrokeWidth,
                    strokeWidthSpeedBgInPX)
                strokeWidthSpeedIndicatorInPX = typedArray.getDimension(R.styleable.SpeedView_speedIndicatorStrokeWidth,
                    strokeWidthSpeedIndicatorInPX)
            } finally {
                //typeArray recycled after use
                typedArray.recycle()
            }

        }

        //lets also prepare the Paint for all the onDraw() operations
        paintSpeedBg.apply {
            color = adjustAlpha(drawColor, 0.3f)
            style = Paint.Style.STROKE
            strokeWidth = strokeWidthSpeedBgInPX
        }
        paintSpeedIndicator.apply {
            color = drawColor
            style = Paint.Style.STROKE
            strokeWidth = strokeWidthSpeedIndicatorInPX
            strokeCap = Paint.Cap.ROUND
        }
    }

    private fun adjustAlpha(color: Int, factor: Float): Int {
        val alpha = Math.round(Color.alpha(color) * factor)
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        //return the faint color for the background
        return Color.argb(alpha, red, green, blue)
    }


    //helper method to convert SP(Scale Independent Pixel) to Pixels
    /*private fun spToPX(spValue: Int, context: Context): Float{
        return spValue * context.resources.displayMetrics.scaledDensity
    }*/

    //helper method to convert DP(Density Independent Pixel) to Pixels
    private fun dpToPX(dpValue: Int, context: Context): Float{
        return dpValue * context.resources.displayMetrics.density
    }



    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int){
        //super.onMeasure()
        val height = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        val weight = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)

        val minDimens = Math.min(weight, height)

        //the actual size of the view
        setMeasuredDimension(minDimens, minDimens)

        //positioning for the speed indicator arc
        rectFSpeedIndicator.set(strokeWidthSpeedIndicatorInPX/2f, strokeWidthSpeedIndicatorInPX/2f,
            minDimens - strokeWidthSpeedIndicatorInPX/2f, minDimens - strokeWidthSpeedIndicatorInPX/2f)

        //positioning for the speed background oval
        rectFSpeedBg.set(strokeWidthSpeedIndicatorInPX + strokeWidthSpeedBgInPX/2f,
            strokeWidthSpeedIndicatorInPX + strokeWidthSpeedBgInPX/2f,
            minDimens - strokeWidthSpeedIndicatorInPX - strokeWidthSpeedBgInPX/2,
            minDimens - strokeWidthSpeedIndicatorInPX - strokeWidthSpeedBgInPX/2)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //draw the speed indicator's background oval
        canvas?.drawOval(rectFSpeedBg, paintSpeedBg)

        //draw the speed indicator itself
        val sweepAngle = 360 * (currentSpeed / max) * 1f
        canvas?.drawArc(rectFSpeedIndicator, startAngle, sweepAngle, false, paintSpeedIndicator)
    }

    /**
     *The following methods are public getters and setters for this custom view fields
     */
    fun getSpeedIndicatorStrokeWidth(): Float{
        return strokeWidthSpeedIndicatorInPX
    }
    fun setSpeedIndicatorStrokeWidth(strokeWidth: Float){
        this.strokeWidthSpeedIndicatorInPX = strokeWidth
        paintSpeedIndicator.strokeWidth = strokeWidth
        invalidate() //to redraw the custom view
        requestLayout() //Because it should recalculate its bounds
    }

    fun getSpeedBackgroundStrokeWidth(): Float{
        return strokeWidthSpeedBgInPX
    }
    fun setSpeedBackgroundStrokeWidth(strokeWidth: Float){
        this.strokeWidthSpeedBgInPX = strokeWidth
        paintSpeedBg.strokeWidth = strokeWidth
        invalidate() //to redraw the custom view
        requestLayout() //Because it should recalculate its bounds
    }

    fun getCurrentSpeed(): Float{
        return currentSpeed
    }
    fun setCurrentSpeed(speed: Float){
        this.currentSpeed = speed
        invalidate()
        requestLayout()
    }

    fun getMinimumSpeed(): Int{
        return min
    }
    fun setMinimumSpeed(minimum: Int){
        this.min = minimum
        invalidate()
    }

    fun getMaximumSpeed(): Int{
        return max
    }
    fun setMaximumSpeed(maximum: Int){
        this.max = maximum
        invalidate()
    }

    fun getDrawColor(): Int{
        return drawColor
    }
    fun setDrawColor(color: Int){
        this.drawColor = color
        paintSpeedBg.color = adjustAlpha(color, 0.3f)
        paintSpeedIndicator.color = color
        invalidate()
        requestLayout()
    }

    /*//helper method to lighten a given color my a modification factor
    @TargetApi(26)
    fun lightenColor(color: Int, factor: Float): Int{
        val red = Color.red(color) * factor
        val green = Color.green(color) * factor
        val blue = Color.blue(color) * factor
        val alpha = Color.alpha(color) * 1f //this remains the same as the passed color
        return Color.argb(alpha, red, green, blue)
    }*/

    fun setCurrentSpeedWithAnimation(speed: Int){
        val speedValue = speed * 1f
        val animator = ObjectAnimator.ofFloat(this, "currentSpeed", speedValue)
        animator.duration = 1000
        animator.start()
    }
}