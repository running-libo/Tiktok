package com.bytedance.tiktok.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import com.bytedance.tiktok.R;

/**
 * create by libo
 * create on 2020-05-20
 * description
 */
public class ControllerView extends RelativeLayout {

    public ControllerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_controller, this);
    }
}
