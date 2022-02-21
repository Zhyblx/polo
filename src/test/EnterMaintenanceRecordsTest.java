package test;

import org.zhangyibin.maintenanceitem.EnterMaintenanceRecords;

/**
 * 输入保养记录测试
 *
 * @author zhangyibin
 */
public class EnterMaintenanceRecordsTest {

    public static void main(String[] args) throws Exception {
        EnterMaintenanceRecords enterMaintenanceRecords = new EnterMaintenanceRecords();
        System.out.println(enterMaintenanceRecords
                .onEnterMaintenanceRecords("", "机油", "2021-10-27", 106737));

    }

}
