package com.bytedance.tiktok.application

import android.app.Application
import com.bytedance.tiktok.base.AppBlockCanaryContext
import com.didichuxing.doraemonkit.DoraemonKit
import com.github.moduth.blockcanary.BlockCanary

/**
 * create by libo
 * create on 2020-05-19
 * description 全局Application
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        DoraemonKit.install(this, "pId")

        BlockCanary.install(this, AppBlockCanaryContext()).start()
    }
}