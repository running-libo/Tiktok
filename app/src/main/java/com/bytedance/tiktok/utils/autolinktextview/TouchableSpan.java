package com.bytedance.tiktok.utils.autolinktextview;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;

abstract class TouchableSpan extends ClickableSpan {

    private boolean isPressed;
    private int normalTextColor;
    private int pressedTextColor;
    private boolean isUnderLineEnabled;

    TouchableSpan(int normalTextColor, int pressedTextColor, boolean isUnderLineEnabled) {
        this.normalTextColor = normalTextColor;
        this.pressedTextColor = pressedTextColor;
        this.isUnderLineEnabled = isUnderLineEnabled;
    }

    void setPressed(boolean isSelected) {
        isPressed = isSelected;
    }

    @Override
    public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        int textColor = isPressed ? pressedTextColor : normalTextColor;
        textPaint.setColor(textColor);
        textPaint.bgColor = Color.TRANSPARENT;
        textPaint.setUnderlineText(isUnderLineEnabled);
    }
}