package com.bytedance.tiktok.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView
import com.bytedance.tiktok.R

class CircleImageView : AppCompatImageView {
    // paint when user press
    private var pressPaint: Paint? = null
    private var width: Float = 0f
    private var height: Float = 0f

    // border color
    private var borderColor = 0

    // width of border
    private var borderWidth = 0

    // alpha when pressed
    private var pressAlpha = 0

    // color when pressed
    private var pressColor = 0

    // radius
    private var radius = 0

    // rectangle or round, 1 is circle, 2 is rectangle
    private var shapeType = 0

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        //init the value
        borderWidth = 0
        borderColor = -0x22000001
        pressAlpha = 0x42
        pressColor = 0x42000000
        radius = 16
        shapeType = 0

        // get attribute of EaseImageView
        if (attrs != null) {
            val array = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
            borderColor = array.getColor(R.styleable.CircleImageView_ease_border_color, borderColor)
            borderWidth = array.getDimensionPixelOffset(R.styleable.CircleImageView_ease_border_width, borderWidth)
            pressAlpha = array.getInteger(R.styleable.CircleImageView_ease_press_alpha, pressAlpha)
            pressColor = array.getColor(R.styleable.CircleImageView_ease_press_color, pressColor)
            radius = array.getDimensionPixelOffset(R.styleable.CircleImageView_ease_radius, radius)
            shapeType = array.getInteger(R.styleable.CircleImageView_es_shape_type, shapeType)
            array.recycle()
        }

        // set paint when pressed
        pressPaint = Paint()
        pressPaint!!.isAntiAlias = true
        pressPaint!!.style = Paint.Style.FILL
        pressPaint!!.color = pressColor
        pressPaint!!.alpha = 0
        pressPaint!!.flags = Paint.ANTI_ALIAS_FLAG
        isDrawingCacheEnabled = true
        setWillNotDraw(false)
    }

    override fun onDraw(canvas: Canvas) {
        if (shapeType == 0) {
            super.onDraw(canvas)
            return
        }
        val drawable = drawable ?: return
        // the width and height is in xml file
        if (getWidth() == 0 || getHeight() == 0) {
            return
        }
        val bitmap = getBitmapFromDrawable(drawable)
        drawDrawable(canvas, bitmap)
        if (isClickable) {
            drawPress(canvas)
        }
        drawBorder(canvas)
    }

    /**
     * draw Rounded Rectangle
     *
     * @param canvas
     * @param bitmap
     */
    @SuppressLint("WrongConstant")
    private fun drawDrawable(canvas: Canvas, bitmap: Bitmap?) {
        var bitmap = bitmap
        val paint = Paint()
        paint.color = -0x1
        paint.isAntiAlias = true //smooths out the edges of what is being drawn
        val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        // set flags
        val saveFlags = Canvas.ALL_SAVE_FLAG
        canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null, saveFlags)
        if (shapeType == 1) {
            canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), (width / 2 - 1).toFloat(), paint)
        } else if (shapeType == 2) {
            val rectf = RectF(1f, 1f, (getWidth() - 1).toFloat(), (getHeight() - 1).toFloat())
            canvas.drawRoundRect(rectf, (radius + 1).toFloat(), (radius + 1).toFloat(), paint)
        }
        paint.xfermode = xfermode
        val scaleWidth = getWidth().toFloat() / bitmap!!.width
        val scaleHeight = getHeight().toFloat() / bitmap.height
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)

        //bitmap scale
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        canvas.restore()
    }

    /**
     * draw the effect when pressed
     *
     * @param canvas
     */
    private fun drawPress(canvas: Canvas) {
        // check is rectangle or circle
        if (shapeType == 1) {
            canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), (width / 2 - 1).toFloat(), pressPaint!!)
        } else if (shapeType == 2) {
            val rectF = RectF(1f, 1f, (width - 1).toFloat(), (height - 1).toFloat())
            canvas.drawRoundRect(rectF, (radius + 1).toFloat(), (radius + 1).toFloat(), pressPaint!!)
        }
    }

    /**
     * draw customized border
     *
     * @param canvas
     */
    private fun drawBorder(canvas: Canvas) {
        if (borderWidth > 0) {
            val paint = Paint()
            paint.strokeWidth = borderWidth.toFloat()
            paint.style = Paint.Style.STROKE
            paint.color = borderColor
            paint.isAntiAlias = true
            // // check is rectangle or circle
            if (shapeType == 1) {
                canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), ((width - borderWidth) / 2).toFloat(), paint)
            } else if (shapeType == 2) {
                val rectf = RectF((borderWidth / 2).toFloat(), (borderWidth / 2).toFloat(), (getWidth() - borderWidth / 2).toFloat(),
                        (getHeight() - borderWidth / 2).toFloat())
                canvas.drawRoundRect(rectf, radius.toFloat(), radius.toFloat(), paint)
            }
        }
    }

    /**
     * monitor the size change
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        width = w.toFloat()
        height = h.toFloat()
    }

    /**
     * monitor if touched
     *
     * @param event
     * @return
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                pressPaint!!.alpha = pressAlpha
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                pressPaint!!.alpha = 0
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
            }
            else -> {
                pressPaint!!.alpha = 0
                invalidate()
            }
        }
        return super.onTouchEvent(event)
    }

    /**
     * @param drawable
     * @return
     */
    private fun getBitmapFromDrawable(drawable: Drawable?): Bitmap? {
        if (drawable == null) {
            return null
        }
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }
        var bitmap: Bitmap?
        val width = Math.max(drawable.intrinsicWidth, 2)
        val height = Math.max(drawable.intrinsicHeight, 2)
        try {
            bitmap = Bitmap.createBitmap(width, height, BITMAP_CONFIG)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            bitmap = null
        }
        return bitmap
    }

    /**
     * set border color
     *
     * @param borderColor
     */
    fun setBorderColor(borderColor: Int) {
        this.borderColor = borderColor
        invalidate()
    }

    /**
     * set border width
     *
     * @param borderWidth
     */
    fun setBorderWidth(borderWidth: Int) {
        this.borderWidth = borderWidth
    }

    /**
     * set alpha when pressed
     *
     * @param pressAlpha
     */
    fun setPressAlpha(pressAlpha: Int) {
        this.pressAlpha = pressAlpha
    }

    /**
     * set color when pressed
     *
     * @param pressColor
     */
    fun setPressColor(pressColor: Int) {
        this.pressColor = pressColor
    }

    /**
     * set radius
     *
     * @param radius
     */
    fun setRadius(radius: Int) {
        this.radius = radius
        invalidate()
    }

    /**
     * set shape,1 is circle, 2 is rectangle
     *
     * @param shapeType
     */
    fun setShapeType(shapeType: Int) {
        this.shapeType = shapeType
        invalidate()
    }

    companion object {
        // default bitmap config
        private val BITMAP_CONFIG = Bitmap.Config.ARGB_8888
        private const val COLORDRAWABLE_DIMENSION = 1
    }
}