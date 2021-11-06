package com.bytedance.tiktok.view

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.widget.ImageView
import android.widget.RelativeLayout
import com.bytedance.tiktok.R
import com.bytedance.tiktok.utils.AnimUtils
import java.util.*

/**
 * create by libo
 * create on 2020-05-20
 * description 点赞动画view
 */
class LikeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    interface OnPlayPauseListener {
        fun onPlayOrPause()
    }

    interface OnLikeListener {
        fun onLikeListener()
    }

    private var gestureDetector: GestureDetector? = null

    /** 图片大小  */
    private val likeViewSize = 330
    private val angles = intArrayOf(-30, 0, 30)

    var playPauseListener: OnPlayPauseListener? = null
    var likeListener: OnLikeListener? = null

    private fun init() {
        gestureDetector = GestureDetector(object : SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent): Boolean {
                addLikeView(e)
                likeListener?.onLikeListener()
                return true
            }

            override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                playPauseListener?.onPlayOrPause()
                return true
            }
        })
        setOnTouchListener { _: View?, event: MotionEvent? ->
            gestureDetector?.onTouchEvent(event)
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
        val animationSet = AnimationSet(true).apply {
            val degrees = angles[Random().nextInt(3)]
            addAnimation(AnimUtils.rotateAnim(0, 0, degrees.toFloat()))
            addAnimation(AnimUtils.scaleAnim(100, 2f, 1f, 0))
            addAnimation(AnimUtils.alphaAnim(0f, 1f, 100, 0))
            addAnimation(AnimUtils.scaleAnim(500, 1f, 1.8f, 300))
            addAnimation(AnimUtils.alphaAnim(1f, 0f, 500, 300))
            addAnimation(AnimUtils.translationAnim(500, 0f, 0f, 0f, -400f, 300))
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {
                    // TODO: remove this unappreciated code
                    Handler().post {
                        removeView(view)
                    }
                }

                override fun onAnimationRepeat(animation: Animation) {}
            })
        }
        view.startAnimation(animationSet)
    }

    init {
        init()
    }
}