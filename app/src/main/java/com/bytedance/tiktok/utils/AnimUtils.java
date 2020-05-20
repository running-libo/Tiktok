package com.bytedance.tiktok.utils;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * create by libo
 * create on 2020-05-20
 * description
 */
public class AnimUtils {

    /**
     * 以中心缩放动画
     *
     * @param from
     * @param to
     */
    public static ScaleAnimation scaleAnim(long time, float from, float to, long offsetTime) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(from, to, from, to,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setStartOffset(offsetTime);
        scaleAnimation.setInterpolator(new DecelerateInterpolator());
        scaleAnimation.setDuration(time);
        return scaleAnimation;
    }

    /**
     * 旋转动画
     *
     * @param time
     */
    public static RotateAnimation rotateAnim(long time, int fromDegrees, float toDegrees) {
        RotateAnimation rotateAnimation = new RotateAnimation(fromDegrees, toDegrees,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(time);
        return rotateAnimation;
    }

    /**
     * 移动动画
     *
     * @param fromX
     * @param toX
     * @param fromY
     * @param toY
     */
    public static TranslateAnimation translationAnim(long time, float fromX, float toX, float fromY, float toY, long offsetTime) {
        TranslateAnimation anim = new TranslateAnimation(fromX, toX, fromY, toY);
        anim.setDuration(time);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.setStartOffset(offsetTime);
        return anim;
    }

    /**
     * 透明度动画
     *
     * @param fromAlpha
     * @param toAlpha
     * @param duration
     */
    public static AlphaAnimation alphaAnim(float fromAlpha, float toAlpha, long duration, long offsetTime) {
        AlphaAnimation anim = new AlphaAnimation(fromAlpha, toAlpha);
        anim.setDuration(duration);
        anim.setStartOffset(offsetTime);
        return anim;
    }
}
