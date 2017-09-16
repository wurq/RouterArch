package com.wurq.base.util;

import java.math.BigDecimal;

/**
 * Created by yangdingguo on 2017/7/12.
 * 时间类处理函数
 */

public class DateUtil {
    /*
    *小时   如果小于100小时 则保留一位小数四舍五入
    * 大于100 小于1000则不显示小数   超过1000显示 999+
    */
    public static String fomateLearnTime(float hour) {
        StringBuffer stringBuffer = new StringBuffer();
        BigDecimal bdhour = new BigDecimal(hour).setScale(1, BigDecimal.ROUND_HALF_UP);
        if (bdhour.compareTo(new BigDecimal(0f)) <= 0) {
            stringBuffer.append(String.valueOf(0));
        } else {
            if (bdhour.compareTo(new BigDecimal(100F)) < 0) {
                stringBuffer.append(bdhour.toString());
            } else if (bdhour.compareTo(new BigDecimal(1000F)) < 0) {
                stringBuffer.append(bdhour.setScale(0, BigDecimal.ROUND_HALF_DOWN).toString());
            } else {
                stringBuffer.append("999+");
            }
        }

        return stringBuffer.toString();
    }

}
