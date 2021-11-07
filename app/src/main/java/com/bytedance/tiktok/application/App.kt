package com.bytedance.tiktok.application

import android.app.Application
import android.content.Context
import com.bytedance.tiktok.base.AppBlockCanaryContext
import com.github.moduth.blockcanary.BlockCanary

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
        BlockCanary.install(this, AppBlockCanaryContext()).start()
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}