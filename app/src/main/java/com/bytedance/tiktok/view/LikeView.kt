package com.bytedance.tiktok.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import com.bytedance.tiktok.R
import com.bytedance.tiktok.utils.AnimUtils
import java.util.*

/**
 * create by libo
 * create on 2020-05-20
 * description 点赞动画view
 */
class LikeView : RelativeLayout {
    private var gestureDetector: GestureDetector? = null

    /** 图片大小  */
    private val likeViewSize = 330
    private val angles = intArrayOf(-30, 0, 30)

    /** 单击是否有点赞效果  */
    private val canSingleTabShow = false
    private var onPlayPauseListener: OnPlayPauseListener? = null
    private var onLikeListener: OnLikeListener? = null


    private var lastClickTime : Long = 0L
    private val MAX_DOUBLE_CLICK_INTERVAL = 300L
    private var isLikeMode = false
    private var isLongPress = false

    private val handler = Handler(Looper.getMainLooper())

    private val singleTapRunnable = Runnable{
        onPlayPauseListener?.onPlayOrPause()
    }

    private val exitLikeModeRunnable = Runnable{
        isLikeMode = false
    }



    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun init() {
        gestureDetector = GestureDetector(object : SimpleOnGestureListener() {
            override fun onLongPress(e: MotionEvent) {
                isLongPress = true
                Toast.makeText(context, "长按了", Toast.LENGTH_SHORT).show()

            }


        })
        setOnTouchListener { v: View?, event: MotionEvent ->
            gestureDetector!!.onTouchEvent(event)

            when(event.action){
                MotionEvent.ACTION_DOWN ->{
                    val nowClickTime = System.currentTimeMillis()
                    val duration = nowClickTime - lastClickTime   //如果不是双击，那么duration肯定会 > MAX_INTERVAL
                    if (duration <= MAX_DOUBLE_CLICK_INTERVAL || isLikeMode){
                        isLikeMode = true
                        handler.removeCallbacks(exitLikeModeRunnable)
                        handler.removeCallbacks(singleTapRunnable)
                        handler.postDelayed(exitLikeModeRunnable, MAX_DOUBLE_CLICK_INTERVAL)

                        addLikeView(event)
                        onLikeListener!!.onLikeListener()

                    }
                    lastClickTime = nowClickTime
                }
                MotionEvent.ACTION_UP ->{
                    if( isLongPress  || isLikeMode){ //长按/点赞后,不触发暂停
                        isLongPress = false
                    }
                    else {
                        handler.postDelayed(singleTapRunnable, MAX_DOUBLE_CLICK_INTERVAL)
                    }
                }
            }



            true
        }
    }

    private fun addLikeView(e: MotionEvent) {
        val imageView = ImageView(context)
        imageView.setImageResource(R.mipmap.ic_like)
        addView(imageView)
        val layoutParams = LayoutParams(likeViewSize, likeViewSize)
        layoutParams.leftMargin = e.x.toInt() - likeViewSize / 2
        layoutParams.topMargin = e.y.toInt() - likeViewSize
        imageView.layoutParams = layoutParams
        playAnim(imageView)
    }

    private fun playAnim(view: View) {
        val animationSet = AnimationSet(true)
        val degrees = angles[Random().nextInt(3)]
        animationSet.addAnimation(AnimUtils.rotateAnim(0, 0, degrees.toFloat()))
        animationSet.addAnimation(AnimUtils.scaleAnim(100, 2f, 1f, 0))
        animationSet.addAnimation(AnimUtils.alphaAnim(0f, 1f, 100, 0))
        animationSet.addAnimation(AnimUtils.scaleAnim(500, 1f, 1.8f, 300))
        animationSet.addAnimation(AnimUtils.alphaAnim(1f, 0f, 500, 300))
        animationSet.addAnimation(AnimUtils.translationAnim(500, 0f, 0f, 0f, -400f, 300))
        animationSet.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                Handler().post { removeView(view) }
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        view.startAnimation(animationSet)
    }

    interface OnPlayPauseListener {
        fun onPlayOrPause()
    }

    /**
     * 设置单机播放暂停事件
     * @param onPlayPauseListener
     */
    fun setOnPlayPauseListener(onPlayPauseListener: OnPlayPauseListener?) {
        this.onPlayPauseListener = onPlayPauseListener
    }

    interface OnLikeListener {
        fun onLikeListener()
    }

    /**
     * 设置双击点赞事件
     * @param onLikeListener
     */
    fun setOnLikeListener(onLikeListener: OnLikeListener?) {
        this.onLikeListener = onLikeListener
    }
}