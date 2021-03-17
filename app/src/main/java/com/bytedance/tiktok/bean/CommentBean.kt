package com.bytedance.tiktok.bean

import com.bytedance.tiktok.bean.VideoBean.UserBean

/**
 * create by libo
 * create on 2020-06-04
 * description
 */
class CommentBean {
    var content: String? = null
        get() = if (field == null) "" else field
    var userBean: UserBean? = null
    var likeCount = 0
    var isLiked = false
}