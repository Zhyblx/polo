package org.zhangyibin.maintenanceitem;

import org.zhangyibin.service.PoloParameter;
import org.zhangyibin.service.ProductEnum;
import org.zhangyibin.service.Report;
import org.zhangyibin.service.Rule;
import org.zhangyibin.util.Intervals;

import java.util.HashMap;
import java.util.Map;

/**
 * 预测下一次所需保养的项目
 *
 * @author zhangyibin
 */
public class WaitingMaintenanceItem {

    private final Map<String, String> timeReportMap = new HashMap<>(); // 时间记录map
    private final Map<String, Integer> timeRuleMap = new HashMap<>(); // 时间规则map
    private final Map<String, Integer> mileageReportMap = new HashMap<>(); // 里程记录map
    private final Map<String, Integer> mileageRuleMap = new HashMap<>();// 里程规则map

    /*
     * 私有化构造<br>
     *
     * 说明：将每个产品的保养规则和保养记录进行初始化
     *
     */
    private WaitingMaintenanceItem() {
        timeReportMap.putAll(Report.onNearestdate(ProductEnum.轮胎.getProductEnum()));
        timeReportMap.putAll(Report.onNearestdate(ProductEnum.空气滤芯.getProductEnum()));
        timeReportMap.putAll(Report.onNearestdate(ProductEnum.空调滤芯.getProductEnum()));
        timeRuleMap.putAll(Rule.getTimeProduct());

        mileageReportMap.putAll(Report.onMileage(ProductEnum.变速箱油.getProductEnum()));
        mileageReportMap.putAll(Report.onMileage(ProductEnum.防冻液.getProductEnum()));
        mileageReportMap.putAll(Report.onMileage(ProductEnum.火花塞.getProductEnum()));
        mileageReportMap.putAll(Report.onMileage(ProductEnum.汽油滤芯.getProductEnum()));
        mileageReportMap.putAll(Report.onMileage(ProductEnum.刹车油.getProductEnum()));
        mileageReportMap.putAll(Report.onMileage(ProductEnum.机油.getProductEnum()));
        mileageRuleMap.putAll(Rule.getMileageProduct());

    }

    /**
     * 预测下一次所需保养的项目(单例模式)
     *
     * @author zhangyibin
     */
    public static WaitingMaintenanceItem getWaitingMaintenanceItem() {
        return new WaitingMaintenanceItem();

    }

    private final String isTrue = "true";
    private final String isFalse = "false";

    private PoloParameter poloParameter = new PoloParameter();

    /*
     * 时间计算器
     *
     * 描述：计算当前时间距离上一次保养时间的间隔天数
     */
    private String onTimeCalculator(String productParameter) {
        String result = "";
        String toDate = poloParameter.toDate(); // 当前时间
        String toNearestdate = timeReportMap.get(productParameter); // 最近保养时间
        long interval = Intervals.onIntervals(toDate, toNearestdate); // 已使用时间
        long timeRule = timeRuleMap.get(productParameter); //时间规则
        long waitingTime = interval - timeRule; // 可继续使用时间
        if (waitingTime >= 0) {
            result = isTrue + "," + waitingTime;
            //list.add(isTrue);

        } else {
            result = isFalse + "," + waitingTime;

        }
        return result;

    }

    /*
     * 里程计算器
     *
     * 描述：计算当前里程距离上一次保养里程的已使用里程数
     */
    private String onMileageCalculator(String productParameter) {
        String result = "";
        poloParameter.setMileage(this.getCurrentMileage());
        int currentMileage = poloParameter.toMileage(); // 当前里程(即，已使用里程数)
        int recentMileage = mileageReportMap.get(productParameter); //最近保养里程
        int mileageRule = mileageRuleMap.get(productParameter); // 里程规则
        int interval = currentMileage - recentMileage; // 已使用里程
        int waitingMileage = interval - mileageRule;
        if (waitingMileage >= 0) {
            result = isTrue + "," + waitingMileage;

        } else {
            result = isFalse + "," + waitingMileage;

        }
        return result;

    }

    /**
     * 是否跟换轮胎
     *
     * @author zhangyibin
     */
    public String isChangeTires() {
        return this.onTimeCalculator(ProductEnum.轮胎.getProductEnum());

    }

    /**
     * 是否更换空气滤芯
     *
     * @author zhangyibin
     */
    public String isChangeReplaceAirFilter() {
        return this.onTimeCalculator(ProductEnum.空气滤芯.getProductEnum());

    }

    /**
     * 是否更换空调滤芯
     *
     * @author zhangyibin
     */
    public String isChangeAirConditioningFilter() {
        return this.onTimeCalculator(ProductEnum.空调滤芯.getProductEnum());

    }

    private int currentMileage = 0;

    /**
     * 设置当前里程数
     *
     * @param currentMileage
     */
    public void setCurrentMileage(int currentMileage) {
        this.currentMileage = currentMileage;

    }

    /**
     * 返回当前里程
     *
     * @return
     */
    public int getCurrentMileage() {
        return this.currentMileage;

    }

    /**
     * 是否更换变速箱油
     *
     * @author zhangyibin
     */
    public String isChangeGearboxOil() {
        return this.onMileageCalculator(ProductEnum.变速箱油.getProductEnum());

    }

    /**
     * 是否更换防冻液
     *
     * @author zhangyibin
     */
    public String isChangeAntifreeze() {
        return this.onMileageCalculator(ProductEnum.防冻液.getProductEnum());

    }

    /**
     * 是否更换火花塞
     *
     * @author zhangyibin
     */
    public String isChangeSparkPlug() {
        return this.onMileageCalculator(ProductEnum.火花塞.getProductEnum());

    }

    /**
     * 是否更换汽油滤芯
     *
     * @author zhangyibin
     */
    public String isChangeGasolineFilter() {
        return this.onMileageCalculator(ProductEnum.汽油滤芯.getProductEnum());

    }

    /**
     * 是否更换刹车油
     *
     * @author zhangyibin
     */
    public String isChangeBrakeFluid() {
        return this.onMileageCalculator(ProductEnum.刹车油.getProductEnum());

    }

    /**
     * 是否更换机油
     *
     * @author zhangyibin
     */
    public String isChangeEngineOil() {
        return this.onMileageCalculator(ProductEnum.机油.getProductEnum());

    }
}
