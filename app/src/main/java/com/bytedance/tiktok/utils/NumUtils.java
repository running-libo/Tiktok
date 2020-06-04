package com.bytedance.tiktok.utils;

import java.text.DecimalFormat;

/**
 * create by libo
 * create on 2020-06-04
 * description
 */
public class NumUtils {

    /**
     * 数字上万过滤
     * @return
     */
    public static String numberFilter(int number) {
        if (number > 9999 && number <= 999999) {  //数字上万，小于百万，保留一位小数点
            DecimalFormat df2 = new DecimalFormat("##.#");
            String format = df2.format((float)number / 10000);
            return format + "万";
        } else if (number > 999999 && number < 99999999) {  //百万到千万不保留小数点
            return number / 10000 + "万";
        } else if (number > 99999999) { //上亿
            DecimalFormat df2 = new DecimalFormat("##.#");
            String format = df2.format((float)number / 100000000);
            return format + "亿";
        } else {
            return number + "";
        }
    }
}
