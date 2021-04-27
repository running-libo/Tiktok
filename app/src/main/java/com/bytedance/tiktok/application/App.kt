package com.bytedance.tiktok.application

import android.app.Application
import com.didichuxing.doraemonkit.DoraemonKit

/**
 * create by libo
 * create on 2020-05-19
 * description 全局Application
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        DoraemonKit.install(this, "pId")
    }
}