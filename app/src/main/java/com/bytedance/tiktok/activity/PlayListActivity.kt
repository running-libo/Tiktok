package com.bytedance.tiktok.activity

import com.bytedance.tiktok.R
import com.bytedance.tiktok.base.BaseActivity
import com.bytedance.tiktok.fragment.RecommendFragment

/**
 * create by libo
 * create on 2020-05-24
 * description 视频全屏播放页
 */
class PlayListActivity : BaseActivity() {

    override fun setLayoutId(): Int {
        return R.layout.activity_play_list
    }

    override fun init() {
        supportFragmentManager.beginTransaction().add(R.id.framelayout, RecommendFragment()).commit()
    }

    companion object {
        @JvmField
        var initPos = 0
    }
}