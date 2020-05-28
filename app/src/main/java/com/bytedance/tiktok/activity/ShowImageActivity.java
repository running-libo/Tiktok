package com.bytedance.tiktok.activity;

import android.widget.ImageView;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.base.BaseActivity;
import butterknife.BindView;

public class ShowImageActivity extends BaseActivity {
    @BindView(R.id.iv_head)
    ImageView ivHead;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_show_image;
    }

    @Override
    protected void init() {
        ivHead.setOnClickListener(v -> finish());
    }
}
