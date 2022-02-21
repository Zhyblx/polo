package org.zhangyibin.maintenanceitem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * 保养信息查询
 *
 * @author zhangyibin
 */
public class MaintenanceInformationQuery {

    private final int currentMileage; // 当前里程数据

    /*
     * 私有化构造
     */
    private MaintenanceInformationQuery(int currentMileage) {
        this.currentMileage = currentMileage;

    }

    /**
     * 保养信息查询类(单例模式)
     *
     * @param currentMileage(当前里程数据为必填项)
     * @return
     * @author zhangyibin
     */
    public static MaintenanceInformationQuery getMaintenanceInformationQuery(int currentMileage) {
        return new MaintenanceInformationQuery(currentMileage);

    }

    /*
     * JSON格式转换器
     */
    private static JSONObject getJson(String name, String productName) {
        JSONObject resultJson = new JSONObject();
        List<String> tiresList = Arrays.asList(productName.split(","));
        resultJson.put("name", name);
        resultJson.put("isChange", tiresList.get(0));
        resultJson.put("differenceNumber", tiresList.get(1));
        return resultJson;

    }


    /**
     * 根据距离上一次的保养时间和里程得出本次保养所需维护的项目
     *
     * @return Json字符串
     * @author zhangyibin
     */
    public String onMaintenanceInformationQuery() {
        WaitingMaintenanceItem waitingMaintenanceItem = WaitingMaintenanceItem.getWaitingMaintenanceItem();
        JSONObject jsonObject = new JSONObject();

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(MaintenanceInformationQuery.getJson("轮胎", waitingMaintenanceItem.isChangeTires()));
        jsonArray.put(MaintenanceInformationQuery.getJson("空气滤芯", waitingMaintenanceItem.isChangeReplaceAirFilter()));
        jsonArray.put(MaintenanceInformationQuery.getJson("空调滤芯", waitingMaintenanceItem.isChangeAirConditioningFilter()));

        waitingMaintenanceItem.setCurrentMileage(this.currentMileage);
        jsonArray.put(MaintenanceInformationQuery.getJson("变速箱油", waitingMaintenanceItem.isChangeGearboxOil()));
        jsonArray.put(MaintenanceInformationQuery.getJson("防冻液", waitingMaintenanceItem.isChangeAntifreeze()));
        jsonArray.put(MaintenanceInformationQuery.getJson("火花塞", waitingMaintenanceItem.isChangeSparkPlug()));
        jsonArray.put(MaintenanceInformationQuery.getJson("汽油滤芯", waitingMaintenanceItem.isChangeGasolineFilter()));
        jsonArray.put(MaintenanceInformationQuery.getJson("刹车油", waitingMaintenanceItem.isChangeBrakeFluid()));
        jsonArray.put(MaintenanceInformationQuery.getJson("机油", waitingMaintenanceItem.isChangeEngineOil()));

        jsonObject.put("return", jsonArray);
        return jsonObject.toString();

    }

}
