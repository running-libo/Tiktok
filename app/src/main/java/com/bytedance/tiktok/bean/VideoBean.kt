package com.bytedance.tiktok.bean

import com.google.android.exoplayer2.source.BaseMediaSource

/**
 * create by libo
 * create on 2020-06-03
 * description 视频实体类
 */
class VideoBean {
    var videoId = 0

    /** 视频播放资源  */
    var videoRes = ""

    /** 封面图片资源  */
    var coverRes = 0

    /** 视频文案内容  */
    var content: String? = null
        get() = if (field == null) "" else field

    /** 作者  */
    var userBean: UserBean? = null

    /** 是否已点赞  */
    var isLiked = false

    /** 与视频发布距离  */
    var distance = 0f

    /** 是否已关注  */
    var isFocused = false

    /** 点赞数  */
    var likeCount = 0

    /** 评论数  */
    var commentCount = 0

    /** 转发数  */
    var shareCount = 0

    /** 本地文件缓存资源 */
    var mediaSource: BaseMediaSource?= null

    class UserBean {
        var uid = 0
        var nickName: String? = null
            get() = if (field == null) "" else field
        var head = 0

        /** 座右铭  */
        var sign: String? = null
            get() = if (field == null) "" else field

        /** 是否已关注  */
        var isFocused = false

        /** 获赞数量  */
        var subCount = 0

        /** 关注数量  */
        var focusCount = 0

        /** 粉丝数量  */
        var fansCount = 0

        /** 作品数量  */
        var workCount = 0

        /** 动态数量  */
        var dynamicCount = 0

        /** 喜欢数量  */
        var likeCount = 0
    }
}