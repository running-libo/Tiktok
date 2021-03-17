package com.bytedance.tiktok.base

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.Unbinder
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar

/**
 * create by libo
 * create on 2020-05-19
 * description activity基类
 */
abstract class BaseActivity : AppCompatActivity() {
    protected var unbinder: Unbinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutId())
        unbinder = ButterKnife.bind(this)
        init()
    }

    protected abstract fun setLayoutId(): Int
    protected abstract fun init()

    /**
     * 设置状态栏颜色
     */
    protected fun setSystemBarColor(color: Int) {
        ImmersionBar.with(this).statusBarColor(color)
    }

    /**
     * 去除状态栏
     */
    protected fun hideStatusBar() {
        ImmersionBar.with(this).hideBar(BarHide.FLAG_HIDE_STATUS_BAR).init()
    }

    /**
     * 保持不息屏
     */
    protected fun keepScreenOn() {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    /**
     * Activity退出动画
     */
    protected fun setExitAnimation(animId: Int) {
        overridePendingTransition(0, animId)
    }

    /**
     * 全屏
     */
    protected fun setFullScreen() {
        ImmersionBar.with(this).init()
    }

    override fun onDestroy() {
        super.onDestroy()
        unbinder!!.unbind()
    }
}