package com.bytedance.tiktok.utils.autolinktextview;

import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

class LinkTouchMovementMethod extends LinkMovementMethod {

    private TouchableSpan pressedSpan;

    @Override
    public boolean onTouchEvent(TextView textView, final Spannable spannable, MotionEvent event) {
        int action = event.getAction();
        boolean isConsume = super.onTouchEvent(textView, spannable, event);
        if (action == MotionEvent.ACTION_DOWN) {
            pressedSpan = getPressedSpan(textView, spannable, event);
            if (pressedSpan != null) {
                pressedSpan.setPressed(true);
                Selection.setSelection(spannable, spannable.getSpanStart(pressedSpan),
                        spannable.getSpanEnd(pressedSpan));
            }
        } else if (action == MotionEvent.ACTION_MOVE) {
            TouchableSpan touchedSpan = getPressedSpan(textView, spannable, event);
            if (pressedSpan != null && touchedSpan != pressedSpan) {
                pressedSpan.setPressed(false);
                pressedSpan = null;
                Selection.removeSelection(spannable);
            }
        } else {
            if (pressedSpan != null) {
                pressedSpan.setPressed(false);
                textView.setClickable(false);
            }
            pressedSpan = null;
            Selection.removeSelection(spannable);
            // 解决clickable与textview 本身click点击事件的冲突。
            if (!isConsume && event.getAction() == MotionEvent.ACTION_UP) {
                ViewParent parent = textView.getParent();
                if (parent instanceof ViewGroup) {
                    // 获取被点击控件的父容器，让父容器执行点击；
                    ((ViewGroup) parent).performClick();
                }
            }
        }
        return isConsume;
    }

    private TouchableSpan getPressedSpan(TextView textView, Spannable spannable, MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        x -= textView.getTotalPaddingLeft();
        y -= textView.getTotalPaddingTop();

        x += textView.getScrollX();
        y += textView.getScrollY();

        Layout layout = textView.getLayout();
        int verticalLine = layout.getLineForVertical(y);
        int horizontalOffset = layout.getOffsetForHorizontal(verticalLine, x);

        TouchableSpan[] link = spannable.getSpans(horizontalOffset, horizontalOffset, TouchableSpan.class);
        TouchableSpan touchedSpan = null;
        if (link.length > 0) {
            touchedSpan = link[0];
        }
        return touchedSpan;
    }
}
