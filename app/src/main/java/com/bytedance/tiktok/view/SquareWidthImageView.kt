package com.bytedance.tiktok.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

/**
 * 高等于宽的imageview
 */
class SquareWidthImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
): AppCompatImageView(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec) //设置高度始终等于宽度，即为正方形
    }
}