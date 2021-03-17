package com.bytedance.tiktok.utils

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.bytedance.tiktok.R
import com.google.android.material.appbar.AppBarLayout

/**
 * create by libo
 * create on 2020-05-22
 * description
 */
class AppbarZoomBehavior(context: Context?, attrs: AttributeSet?) : AppBarLayout.Behavior(context, attrs) {
    private var mImageView: ImageView? = null
    private var mAppbarHeight //记录AppbarLayout原始高度
            = 0
    private var mImageViewHeight //记录ImageView原始高度
            = 0
    private var mTotalDy //手指在Y轴滑动的总距离
            = 0f
    private var mScaleValue //图片缩放比例
            = 0f
    private var mLastBottom //Appbar的变化高度
            = 0
    private var isAnimate //是否做动画标志
            = false

    override fun onLayoutChild(parent: CoordinatorLayout, abl: AppBarLayout, layoutDirection: Int): Boolean {
        val handled = super.onLayoutChild(parent, abl, layoutDirection)
        init(abl)
        return handled
    }

    /**
     * 进行初始化操作，在这里获取到ImageView的引用，和Appbar的原始高度
     *
     * @param abl
     */
    private fun init(abl: AppBarLayout) {
        abl.clipChildren = false
        mAppbarHeight = abl.height
        mImageView = abl.findViewById(R.id.ivBg)
        if (mImageView != null) {
            mImageViewHeight = mImageView!!.height
        }
    }

    /**
     * 是否处理嵌套滑动
     *
     * @param parent
     * @param child
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes
     * @param type
     * @return
     */
    override fun onStartNestedScroll(parent: CoordinatorLayout, child: AppBarLayout, directTargetChild: View, target: View, nestedScrollAxes: Int, type: Int): Boolean {
        isAnimate = true
        return true
    }

    /**
     * 在这里做具体的滑动处理
     *
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dx
     * @param dy
     * @param consumed
     * @param type
     */
    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        if (mImageView != null && child.bottom >= mAppbarHeight && dy < 0 && type == ViewCompat.TYPE_TOUCH) {
            zoomHeaderImageView(child, dy)
        } else {
            if (mImageView != null && child.bottom > mAppbarHeight && dy > 0 && type == ViewCompat.TYPE_TOUCH) {
                consumed[1] = dy
                zoomHeaderImageView(child, dy)
            } else {
                if (valueAnimator == null || !valueAnimator!!.isRunning) {
                    super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
                }
            }
        }
    }

    /**
     * 对ImageView进行缩放处理，对AppbarLayout进行高度的设置
     *
     * @param abl
     * @param dy
     */
    private fun zoomHeaderImageView(abl: AppBarLayout, dy: Int) {
        mTotalDy += -dy.toFloat()
        mTotalDy = Math.min(mTotalDy, MAX_ZOOM_HEIGHT)
        mScaleValue = Math.max(1f, 1f + mTotalDy / MAX_ZOOM_HEIGHT)
        ViewCompat.setScaleX(mImageView, mScaleValue)
        ViewCompat.setScaleY(mImageView, mScaleValue)
        mLastBottom = mAppbarHeight + (mImageViewHeight / 2 * (mScaleValue - 1)).toInt()
        abl.bottom = mLastBottom
    }

    /**
     * 处理惯性滑动的情况
     *
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param velocityX
     * @param velocityY
     * @return
     */
    override fun onNestedPreFling(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View, velocityX: Float, velocityY: Float): Boolean {
        if (velocityY > 100) {
            isAnimate = false
        }
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY)
    }

    /**
     * 滑动停止的时候，恢复AppbarLayout、ImageView的原始状态
     *
     * @param coordinatorLayout
     * @param abl
     * @param target
     * @param type
     */
    override fun onStopNestedScroll(coordinatorLayout: CoordinatorLayout, abl: AppBarLayout, target: View, type: Int) {
        recovery(abl)
        super.onStopNestedScroll(coordinatorLayout, abl, target, type)
    }

    var valueAnimator: ValueAnimator? = null

    /**
     * 通过属性动画的形式，恢复AppbarLayout、ImageView的原始状态
     *
     * @param abl
     */
    private fun recovery(abl: AppBarLayout) {
        if (mTotalDy > 0) {
            mTotalDy = 0f
            if (isAnimate) {
                valueAnimator = ValueAnimator.ofFloat(mScaleValue, 1f).setDuration(220)
                valueAnimator?.addUpdateListener { animation ->
                    val value = animation.animatedValue as Float
                    ViewCompat.setScaleX(mImageView, value)
                    ViewCompat.setScaleY(mImageView, value)
                    abl.bottom = (mLastBottom - (mLastBottom - mAppbarHeight) * animation.animatedFraction).toInt()
                }
                valueAnimator?.start()
            } else {
                ViewCompat.setScaleX(mImageView, 1f)
                ViewCompat.setScaleY(mImageView, 1f)
                abl.bottom = mAppbarHeight
            }
        }
    }

    companion object {
        private const val MAX_ZOOM_HEIGHT = 500f //放大最大高度
    }
}