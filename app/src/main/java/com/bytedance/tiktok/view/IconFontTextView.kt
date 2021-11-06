package com.bytedance.tiktok.view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * 用于加载iconfont的TextView
 */
class IconFontTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
): AppCompatTextView(context, attrs) {

    init {
        typeface = Typeface.createFromAsset(context.assets, "iconfont.ttf")
    }
}