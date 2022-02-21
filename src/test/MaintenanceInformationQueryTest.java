package test;

import org.zhangyibin.maintenanceitem.MaintenanceInformationQuery;

/**
 * 保养信息查询测试
 *
 * @author zhangyibin
 */
public class MaintenanceInformationQueryTest {

    public static void main(String[] args) throws Exception {
        System.out.println(
                MaintenanceInformationQuery.getMaintenanceInformationQuery(115666).onMaintenanceInformationQuery());

    }

}
