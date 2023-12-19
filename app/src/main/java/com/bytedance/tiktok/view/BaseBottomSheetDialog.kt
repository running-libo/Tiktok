package com.bytedance.tiktok.view

import android.content.Context
import android.os.Bundle
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.bytedance.tiktok.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * create by libo
 * create on 2020-05-27
 * description
 */
open class BaseBottomSheetDialog : BottomSheetDialogFragment() {
    private var bottomSheet: FrameLayout? = null
    var behavior: BottomSheetBehavior<FrameLayout>? = null
    override fun onStart() {
        super.onStart()
        val dialog = dialog as BottomSheetDialog
        bottomSheet = dialog.delegate.findViewById(com.google.android.material.R.id.design_bottom_sheet)
        if (bottomSheet != null) {
            val layoutParams = bottomSheet!!.layoutParams as CoordinatorLayout.LayoutParams
            layoutParams.height = height
            bottomSheet!!.layoutParams = layoutParams
            behavior = BottomSheetBehavior.from(bottomSheet!!)
            behavior?.peekHeight = height
            // 初始为展开状态
            behavior?.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.MyDialog)
        isCancelable = true
    }

    protected open val height: Int
        protected get() = resources.displayMetrics.heightPixels

    /**
     * dp转px
     *
     * @param context
     * @param dpValue
     * @return
     */
    protected fun dp2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}