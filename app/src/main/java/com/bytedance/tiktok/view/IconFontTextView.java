package com.bytedance.tiktok.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * create by libo
 * create on 2018/11/20
 * description 用于加载iconfont的TextView
 */
public class IconFontTextView extends AppCompatTextView {
    /** 所有IconFontTextView公用typeface */
    private static Typeface typeface;

    public IconFontTextView(Context context) {
        super(context);
    }

    public IconFontTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        typeface = Typeface.createFromAsset(getContext().getAssets(), "iconfont.ttf");
        setTypeface(typeface);
    }

}
