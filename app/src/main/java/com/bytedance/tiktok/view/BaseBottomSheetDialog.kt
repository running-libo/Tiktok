package com.bytedance.tiktok.view

import android.os.Bundle
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.bytedance.tiktok.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class BaseBottomSheetDialog : BottomSheetDialogFragment() {
    private var bottomSheet: FrameLayout? = null
    var behavior: BottomSheetBehavior<FrameLayout>? = null

    override fun onStart() {
        super.onStart()
        bottomSheet = (dialog as? BottomSheetDialog)?.delegate?.findViewById<FrameLayout>(
            com.google.android.material.R.id.design_bottom_sheet
        )?.also {
            val layoutParams = bottomSheet?.layoutParams as? CoordinatorLayout.LayoutParams
            layoutParams?.height = resources.displayMetrics.heightPixels
            bottomSheet?.layoutParams = layoutParams
            behavior = BottomSheetBehavior.from(it)
            behavior?.peekHeight = resources.displayMetrics.heightPixels
            // 初始为展开状态
            behavior?.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.MyDialog)
        isCancelable = true
    }
}