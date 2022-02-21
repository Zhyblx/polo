package test;

import org.zhangyibin.maintenanceitem.WaitingMaintenanceItem;

/**
 * 预测下一次所需保养的项目测试
 *
 * @author zhangyibin
 */
public class MaintenanceItemTest {

    public static void main(String[] args) throws Exception {
        WaitingMaintenanceItem waitingMaintenanceItem = WaitingMaintenanceItem.getWaitingMaintenanceItem();
        System.out.println("轮胎:" + waitingMaintenanceItem.isChangeTires());
        System.out.println("空气滤芯:" + waitingMaintenanceItem.isChangeReplaceAirFilter());
        System.out.println("空调滤芯:" + waitingMaintenanceItem.isChangeAirConditioningFilter());

        waitingMaintenanceItem.setCurrentMileage(109960);
        System.out.println("变速箱油:" + waitingMaintenanceItem.isChangeGearboxOil());
        System.out.println("防冻液:" + waitingMaintenanceItem.isChangeAntifreeze());
        System.out.println("火花塞:" + waitingMaintenanceItem.isChangeSparkPlug());
        System.out.println("汽油滤芯:" + waitingMaintenanceItem.isChangeGasolineFilter());
        System.out.println("刹车油:" + waitingMaintenanceItem.isChangeBrakeFluid());
        System.out.println("机油:" + waitingMaintenanceItem.isChangeEngineOil());

    }
}
