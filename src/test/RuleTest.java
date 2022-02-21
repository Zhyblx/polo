package test;

import org.zhangyibin.service.ProductEnum;
import org.zhangyibin.service.Rule;

import java.util.HashMap;
import java.util.Map;

/**
 * 规则测试
 *
 * @author zhangyibin
 */
public class RuleTest {

    public static void main(String[] args) throws Exception {
        Map<String, Integer> mileageRuleMap = new HashMap<>();// 里程规则map
        mileageRuleMap.putAll(Rule.getMileageProduct());
        int mileageRule = mileageRuleMap.get(ProductEnum.机油.getProductEnum()); // 里程规则
        System.out.println(mileageRule);

    }

}
