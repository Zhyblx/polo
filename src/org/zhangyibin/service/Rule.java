package org.zhangyibin.service;

import java.util.HashMap;
import java.util.Map;

/**
 * 产品规则查询
 *
 * @author zhangyibin
 */
public class Rule {

    /**
     * 返回时间维度的产品规则<br>
     * <p>
     * 时间维度的产品包括：轮胎、空气滤芯、空调滤芯
     *
     * @author zhangyibin
     */
    public static Map<String, Integer> getTimeProduct() {
        Map<String, Integer> timeProductMap = new HashMap<>();
        timeProductMap.putAll(Report.onProduct(TypeEnum.时间.getTypeEnum()));
        return timeProductMap;

    }

    /**
     * 返回里程维度的产品规则<br>
     * <p>
     * 时间维度的产品包括：变速箱油、防冻液、火花塞、汽油滤芯、刹车油、机油
     *
     * @author zhangyibin
     */
    public static Map<String, Integer> getMileageProduct() {
        Map<String, Integer> mileageProductMap = new HashMap<>();
        mileageProductMap.putAll(Report.onProduct(TypeEnum.里程.getTypeEnum()));
        return mileageProductMap;

    }

}
