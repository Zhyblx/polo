package test;

import org.zhangyibin.maintenanceitem.MaintenanceInformationQuery;
import org.zhangyibin.util.StringConvertArray;

public class StringConvertArrayTest {

    public static void main(String[] args) throws Exception {
        StringConvertArray
                .getStringConvertArray(MaintenanceInformationQuery
                        .getMaintenanceInformationQuery(115666)
                        .onMaintenanceInformationQuery());
    }


}
