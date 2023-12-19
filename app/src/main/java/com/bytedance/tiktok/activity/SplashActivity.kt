package com.bytedance.tiktok.activity

import android.content.Intent
import android.os.CountDownTimer
import com.bytedance.tiktok.base.BaseBindingActivity
import com.bytedance.tiktok.databinding.ActivitySplashBinding

/**
 * create by libo
 * create on 2020/5/19
 * description App启动页面
 */
class SplashActivity : BaseBindingActivity<ActivitySplashBinding>({ActivitySplashBinding.inflate(it)}) {

    override fun init() {
        setFullScreen()
        object : CountDownTimer(500, 500) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        }.start()
    }
}