package com.bytedance.tiktok.activity

import android.view.View
import com.bytedance.tiktok.base.BaseBindingActivity
import com.bytedance.tiktok.databinding.ActivityShowImageBinding

class ShowImageActivity : BaseBindingActivity<ActivityShowImageBinding>({ActivityShowImageBinding.inflate(it)}) {

    override fun init() {
        binding.ivHead.setOnClickListener { v: View? -> finish() }
        val headRes = intent.getIntExtra("res", 0)
        binding.ivHead.setImageResource(headRes)
    }
}