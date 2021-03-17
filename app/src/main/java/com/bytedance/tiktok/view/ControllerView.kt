package com.bytedance.tiktok.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.RelativeLayout
import butterknife.ButterKnife
import com.bytedance.tiktok.R
import com.bytedance.tiktok.bean.VideoBean
import com.bytedance.tiktok.utils.AutoLinkHerfManager
import com.bytedance.tiktok.utils.NumUtils
import com.bytedance.tiktok.utils.OnVideoControllerListener
import kotlinx.android.synthetic.main.view_controller.view.*

/**
 * create by libo
 * create on 2020-05-20
 * description
 */
class ControllerView(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs), View.OnClickListener {
    private var listener: OnVideoControllerListener? = null
    private var videoData: VideoBean? = null

    private fun init() {
        val rootView = LayoutInflater.from(context).inflate(R.layout.view_controller, this)
        ButterKnife.bind(this, rootView)
        ivHead!!.setOnClickListener(this)
        ivComment!!.setOnClickListener(this)
        ivShare!!.setOnClickListener(this)
        rlLike!!.setOnClickListener(this)
        ivFocus!!.setOnClickListener(this)
        setRotateAnim()
    }

    fun setVideoData(videoData: VideoBean) {
        this.videoData = videoData
        ivHead!!.setImageResource(videoData.userBean!!.head)
        tvNickname!!.text = "@" + videoData.userBean!!.nickName
        AutoLinkHerfManager.setContent(videoData.content, autoLinkTextView)
        ivHeadAnim!!.setImageResource(videoData.userBean!!.head)
        tvLikecount!!.text = NumUtils.numberFilter(videoData.likeCount)
        tvCommentcount!!.text = NumUtils.numberFilter(videoData.commentCount)
        tvSharecount!!.text = NumUtils.numberFilter(videoData.shareCount)
        animationView!!.setAnimation("like.json")

        //点赞状态
        if (videoData.isLiked) {
            ivLike!!.setTextColor(resources.getColor(R.color.color_FF0041))
        } else {
            ivLike!!.setTextColor(resources.getColor(R.color.white))
        }

        //关注状态
        if (videoData.isFocused) {
            ivFocus!!.visibility = GONE
        } else {
            ivFocus!!.visibility = VISIBLE
        }
    }

    fun setListener(listener: OnVideoControllerListener?) {
        this.listener = listener
    }

    override fun onClick(v: View) {
        if (listener == null) {
            return
        }
        when (v.id) {
            R.id.iv_head -> listener!!.onHeadClick()
            R.id.rlLike -> {
                listener!!.onLikeClick()
                like()
            }
            R.id.ivComment -> listener!!.onCommentClick()
            R.id.ivShare -> listener!!.onShareClick()
            R.id.ivFocus -> if (!videoData!!.isFocused) {
                videoData!!.isLiked = true
                ivFocus!!.visibility = GONE
            }
        }
    }

    /**
     * 点赞动作
     */
    fun like() {
        if (!videoData!!.isLiked) {
            //点赞
            animationView!!.visibility = VISIBLE
            animationView!!.playAnimation()
            ivLike!!.setTextColor(resources.getColor(R.color.color_FF0041))
        } else {
            //取消点赞
            animationView!!.visibility = INVISIBLE
            ivLike!!.setTextColor(resources.getColor(R.color.white))
        }
        videoData!!.isLiked = !videoData!!.isLiked
    }

    /**
     * 循环旋转动画
     */
    private fun setRotateAnim() {
        val rotateAnimation = RotateAnimation(0f, 359f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotateAnimation.repeatCount = Animation.INFINITE
        rotateAnimation.duration = 8000
        rotateAnimation.interpolator = LinearInterpolator()
        rlRecord!!.startAnimation(rotateAnimation)
    }

    init {
        init()
    }
}