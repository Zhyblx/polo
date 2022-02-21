package org.zhangyibin.service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 设置polo的保养参数(时间和里程)
 *
 * @author zhangyibin
 */

public class PoloParameter {

    public static final int ultimateMileage=999999; // Polo汽车的终极里程数据

    /**
     * 获取当前时间<br>
     * <p>
     * 描述：可作为本次保养时间参数
     */
    public String toDate() {
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");
        Date date = new Date();// 获取当前时间
        String nowadays = sdf.format(date);
        return nowadays;
    }


    private Integer mileage = 0;

    /**
     * 设置当前里程数
     *
     * @param mileage(里程数)
     * @author zhangyibin
     */
    public void setMileage(Integer mileage) {
        this.mileage = mileage;

    }

    /**
     * 返回当前里程数
     *
     * @author zhangyibin
     */
    public Integer toMileage() {
        return mileage;

    }

}
