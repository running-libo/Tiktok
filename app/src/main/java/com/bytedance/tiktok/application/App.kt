package com.bytedance.tiktok.application

import android.app.Application
import com.google.android.exoplayer2.util.Util

/**
 * create by libo
 * create on 2020-05-19
 * description 全局Application
 */
class App : Application() {
    companion object {
        var userAgent: String? = null
    }


    override fun onCreate() {
        super.onCreate()

        userAgent = Util.getUserAgent(this, "Tiktok")
    }
}