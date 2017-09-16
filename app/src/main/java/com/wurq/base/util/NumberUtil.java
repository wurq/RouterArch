package com.wurq.base.util;

/**
 * Created by yangdingguo on 2017/7/12.
 * 数字处理类
 */

public class NumberUtil {

    /*
    * overNum 10   innum 超过 10  以 9+显示
    *
    * */
    public static String numberToString(int inNum, int overNum) {
        StringBuffer stringBuffer = new StringBuffer();
        if (inNum < overNum || overNum < 0) {
            stringBuffer.append(String.valueOf(inNum));
        } else {
            stringBuffer.append(String.valueOf(overNum - 1)).append("+");
        }
        return stringBuffer.toString();
    }
}
