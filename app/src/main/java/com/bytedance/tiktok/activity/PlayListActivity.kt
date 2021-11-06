package com.bytedance.tiktok.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bytedance.tiktok.R
import com.bytedance.tiktok.databinding.ActivityPlayListBinding
import com.bytedance.tiktok.fragment.RecommendFragment

/**
 * 视频全屏播放页
 */
class PlayListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        supportFragmentManager.beginTransaction().add(R.id.framelayout, RecommendFragment.newInstance()).commit()
    }

    companion object {
        @JvmField
        var initPos = 0
    }
}