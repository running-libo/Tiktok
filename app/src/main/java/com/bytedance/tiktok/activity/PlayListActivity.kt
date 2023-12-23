package com.bytedance.tiktok.activity

import com.bytedance.tiktok.R
import com.bytedance.tiktok.base.BaseBindingActivity
import com.bytedance.tiktok.databinding.ActivityPlayListBinding
import com.bytedance.tiktok.fragment.RecommendFragment

/**
 * create by libo
 * create on 2020-05-24
 * description 视频全屏播放页
 */
class PlayListActivity : BaseBindingActivity<ActivityPlayListBinding>({ActivityPlayListBinding.inflate(it)}) {

    override fun init() {
        supportFragmentManager.beginTransaction().add(R.id.framelayout, RecommendFragment()).commit()
    }

    companion object {
        var initPos = 0
    }
}