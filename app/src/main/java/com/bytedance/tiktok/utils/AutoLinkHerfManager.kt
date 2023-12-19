package com.bytedance.tiktok.utils

import android.text.TextUtils
import android.util.Log
import android.view.View
import com.bytedance.tiktok.utils.autolinktextview.AutoLinkMode
import com.bytedance.tiktok.utils.autolinktextview.AutoLinkTextView

/**
 * create by libo
 * create on 2020-05-21
 * description
 */
object AutoLinkHerfManager {
    /**
     * 设置正文内容
     *
     * @param content
     */
    fun setContent(content: String?, autoLinkTextView: AutoLinkTextView) {
        if (TextUtils.isEmpty(content)) return
        autoLinkTextView.visibility = View.VISIBLE
        autoLinkTextView.addAutoLinkMode(AutoLinkMode.MODE_HASHTAG, AutoLinkMode.MODE_MENTION, AutoLinkMode.MODE_URL) //设置需要富文本的模式
        autoLinkTextView.text = content
        autoLinkTextView.setAutoLinkOnClickListener { autoLinkMode: AutoLinkMode?, matchedText: String ->
            when (autoLinkMode) {
                AutoLinkMode.MODE_HASHTAG -> Log.i("minfo", "话题 $matchedText")
                AutoLinkMode.MODE_MENTION -> Log.i("minfo", "at $matchedText")
                else -> {}
            }
        }
    }
}