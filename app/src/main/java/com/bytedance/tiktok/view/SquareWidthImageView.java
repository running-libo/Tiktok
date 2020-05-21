package com.bytedance.tiktok.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * create by libo
 * create on 2020/5/21
 * description 高等于宽的imageview
 */
public class SquareWidthImageView extends AppCompatImageView {

    public SquareWidthImageView(Context context) {
        super(context);
    }

    public SquareWidthImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);  //设置高度始终等于宽度，即为正方形
    }
}
