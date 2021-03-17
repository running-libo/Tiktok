package com.bytedance.tiktok.activity

import android.view.View
import android.widget.ImageView
import butterknife.BindView
import com.bytedance.tiktok.R
import com.bytedance.tiktok.base.BaseActivity

class ShowImageActivity : BaseActivity() {
    @JvmField
    @BindView(R.id.iv_head)
    var ivHead: ImageView? = null

    override fun setLayoutId(): Int {
        return R.layout.activity_show_image
    }

    override fun init() {
        ivHead!!.setOnClickListener { v: View? -> finish() }
        val headRes = intent.getIntExtra("res", 0)
        ivHead!!.setImageResource(headRes)
    }
}