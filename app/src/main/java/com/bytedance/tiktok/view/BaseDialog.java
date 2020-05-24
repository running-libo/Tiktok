package com.bytedance.tiktok.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;
import androidx.annotation.NonNull;

/**
 * create by libo
 * create on 2020-05-23
 * description dialog基类
 */
public abstract class BaseDialog extends Dialog {
    private int gravity = Gravity.BOTTOM;

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public BaseDialog(@NonNull Context context, int themeResId, int gravity) {
        super(context, themeResId);
        this.gravity = gravity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setGravity(gravity);

        initSize();
    }

    /**
     * 设置dialog的宽度铺满屏幕
     */
    private void initSize() {
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth();
        getWindow().setAttributes(p);
    }

}
