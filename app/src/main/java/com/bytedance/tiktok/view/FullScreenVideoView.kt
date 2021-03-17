package com.bytedance.tiktok.view

import android.content.Context
import android.util.AttributeSet
import android.widget.VideoView

/**
 * create by libo
 * create on 2018/12/20
 * description 全屏播放Videoview
 */
class FullScreenVideoView : VideoView {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec))
    }
}