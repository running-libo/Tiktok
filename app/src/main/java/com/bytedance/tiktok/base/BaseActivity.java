package com.bytedance.tiktok.base;

import android.os.Bundle;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * create by libo
 * create on 2020-05-19
 * description activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());

        unbinder = ButterKnife.bind(this);
        init();
    }

    protected abstract int setLayoutId();

    protected abstract void init();

    /**
     * 设置状态栏颜色
     */
    protected void setSystemBarColor(int color) {
        ImmersionBar.with(this).statusBarColor(color);
    }

    /**
     * 去除状态栏
     */
    protected void hideStatusBar() {
        ImmersionBar.with(this).hideBar(BarHide.FLAG_HIDE_STATUS_BAR).init();
    }

    /**
     * 保持不息屏
     */
    protected void keepScreenOn() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    /**
     * Activity退出动画
     */
    protected void setExitAnimation(int animId) {
        overridePendingTransition(0, animId);
    }

    /**
     * 全屏
     */
    protected void setFullScreen() {
        ImmersionBar.with(this).init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }
}
