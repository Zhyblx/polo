package test;

import org.zhangyibin.service.ProductEnum;
import org.zhangyibin.service.Report;

import java.util.Map;

/**
 * ReportTest (测试查询最近一次的保养里程数)
 * @author zhangyibin
 *
 */
public class ReportTest {

    public static void main(String[] args) throws Exception{
        String product=ProductEnum.机油.getProductEnum();

        Map<String,Integer> recentMileage= Report.onMileage(product);
        Map<String,String> recentTime=Report.onNearestdate(product);

        System.out.println("保养里程："+recentMileage.get(product));
        System.out.println("保养时间："+recentTime.get(product));

    }

}
