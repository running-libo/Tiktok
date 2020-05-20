package com.bytedance.tiktok.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

/**
 * create by libo
 * create on 2020/05/20
 * description 可设置可以/不可以滑动的viewpager
 */
public class ScrollableViewPager extends ViewPager {

    private boolean scrollable = true;
    private float startX;

    public ScrollableViewPager(Context context) {
        super(context);
    }

    public ScrollableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (!scrollable) {
            return false;
        }

        return super.onTouchEvent(ev);
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        if (!scrollable) {
//            return false;
//        }
//
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                startX = ev.getX();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (ev.getX() - startX > 20) {  //左滑
//                    return false;  //不拦截事件
//                } else if (ev.getX() - startX < 20){  //右滑
//                    return false;  //不拦截事件
//                }
//                break;
//        }
//
//        return super.onInterceptTouchEvent(ev);
//    }

    public boolean isCanScrollble() {
        return scrollable;
    }

    public void setCanScrollble(boolean scrollble) {
        this.scrollable = scrollble;
    }
}
