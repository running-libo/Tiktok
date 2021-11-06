package com.bytedance.tiktok.bean

import com.bytedance.tiktok.bean.VideoBean.UserBean

class CommentBean {
    var content: String? = null
        get() = field.orEmpty()
    var userBean: UserBean? = null
    var likeCount = 0
}