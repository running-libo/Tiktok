package com.bytedance.tiktok.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.bytedance.tiktok.R
import com.bytedance.tiktok.bean.VideoBean
import com.bytedance.tiktok.databinding.ViewControllerBinding
import com.bytedance.tiktok.utils.AutoLinkHerfManager
import com.bytedance.tiktok.utils.NumUtils
import com.bytedance.tiktok.utils.OnVideoControllerListener

/**
 * create by libo
 * create on 2020-05-20
 * description
 */
class ControllerView(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs),
    View.OnClickListener {

    private var binding: ViewControllerBinding =
        ViewControllerBinding.inflate(LayoutInflater.from(context))

    var listener: OnVideoControllerListener? = null
    private var videoData: VideoBean? = null

    private fun init() {
        addView(binding.root)
        binding.ivHead.setOnClickListener(this)
        binding.ivComment.setOnClickListener(this)
        binding.ivShare.setOnClickListener(this)
        binding.rlLike.setOnClickListener(this)
        binding.ivFocus.setOnClickListener(this)
        setRotateAnim()
    }

    fun setVideoData(videoData: VideoBean) {
        this.videoData = videoData
        videoData.userBean?.head?.let {
            binding.ivHead.setImageResource(it)
        }
        binding.tvNickname.text = "@${videoData.userBean?.nickName}"
        AutoLinkHerfManager.setContent(videoData.content, binding.autoLinkTextView)
        videoData.userBean?.head?.let {
            binding.ivHeadAnim.setImageResource(it)
        }
        binding.tvLikecount.text = NumUtils.numberFilter(videoData.likeCount)
        binding.tvCommentcount.text = NumUtils.numberFilter(videoData.commentCount)
        binding.tvSharecount.text = NumUtils.numberFilter(videoData.shareCount)
        binding.animationView.setAnimation("like.json")

        //点赞状态
        if (videoData.isLiked) {
            binding.ivLike.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.color_FF0041
                )
            )
        } else {
            binding.ivLike.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
        }

        //关注状态
        if (videoData.isFocused) {
            binding.ivFocus.visibility = GONE
        } else {
            binding.ivFocus.visibility = VISIBLE
        }
    }

    override fun onClick(v: View) {
        val listener = listener ?: return
        when (v.id) {
            R.id.ivHead -> listener.onHeadClick()
            R.id.rlLike -> {
                listener.onLikeClick()
                like()
            }
            R.id.ivComment -> listener.onCommentClick()
            R.id.ivShare -> listener.onShareClick()
            R.id.ivFocus -> {
                if (videoData?.isFocused == false) {
                    videoData?.isLiked = true
                    binding.ivFocus.visibility = GONE
                }
            }
        }
    }

    /**
     * 点赞动作
     */
    fun like() {
        if (videoData?.isLiked == false) {
            //点赞
            binding.animationView.visibility = VISIBLE
            binding.animationView.playAnimation()
            binding.ivLike.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.color_FF0041
                )
            )
        } else {
            //取消点赞
            binding.animationView.visibility = INVISIBLE
            binding.ivLike.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
        }
        videoData?.isLiked = !(videoData?.isLiked ?: false)
    }

    /**
     * 循环旋转动画
     */
    private fun setRotateAnim() {
        val rotateAnimation = RotateAnimation(
            0f, 359f,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
        )
        rotateAnimation.repeatCount = Animation.INFINITE
        rotateAnimation.duration = 8000
        rotateAnimation.interpolator = LinearInterpolator()
        binding.rlRecord.startAnimation(rotateAnimation)
    }

    init {
        init()
    }
}