package com.bytedance.tiktok.utils

/**
 * create by libo
 * create on 2020-05-21
 * description 视频控制器点击监听接口
 */
interface OnVideoControllerListener {

    fun onHeadClick()

    fun onLikeClick()

    fun onCommentClick()

    fun onShareClick()
}