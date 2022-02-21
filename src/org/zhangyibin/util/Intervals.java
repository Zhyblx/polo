package org.zhangyibin.util;

import java.text.SimpleDateFormat;

/*
 * 日期间隔计算工具<br>
 *
 * 描述：计算当前时间距离上一次保养时间的间隔天数
 *
 * @author zhangyibin
 */
public class Intervals {

    /**
     * 日期间隔计算工具<br>
     * <p>
     * 描述：计算当前时间距离上一次保养时间的间隔天数
     *
     * @param dateStart(当前日期)
     * @param dateStop(历史日期)
     * @author zhangyibin
     */
    public static long onIntervals(String dateStart, String dateStop) {
        long days = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            long time = sdf.parse(dateStart).getTime();
            long time1 = sdf.parse(dateStop).getTime();
            days = (int) ((time - time1) / (24 * 60 * 60 * 1000));
        } catch (Exception e) {
            e.printStackTrace();

        }
        return days;

    }
}
