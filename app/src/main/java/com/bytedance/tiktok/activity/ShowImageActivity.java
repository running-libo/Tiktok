package com.bytedance.tiktok.activity;

import android.widget.ImageView;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.base.BaseActivity;

public class ShowImageActivity extends BaseActivity {
    private ImageView ivHead;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_show_image;
    }

    @Override
    protected void init() {
        ivHead = findViewById(R.id.iv_head);
        ivHead.setOnClickListener(v -> finish());
    }
}
