package com.bytedance.tiktok.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bytedance.tiktok.databinding.ActivityShowImageBinding

class ShowImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.ivHead.setOnClickListener {
            finish()
        }
        val headRes = intent.getIntExtra("res", 0)
        binding.ivHead.setImageResource(headRes)
    }
}