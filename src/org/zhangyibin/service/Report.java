package org.zhangyibin.service;

import org.zhangyibin.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 上一次保养记录查询
 *
 * @author zhangyibin
 */
public class Report {

    // 私有化构造
    private Report() {
    }

    /**
     * 根据产品类型查询对应产品的保养规则
     *
     * @param type(时间/里程类型)
     * @author zhangyibin
     */
    public static Map<String, Integer> onProduct(String type) {
        Map<String, Integer> map = new HashMap<>();
        String sql = "select * from PRODUCT where TYPE='" + type + "' limit 100;";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ID = rs.getString("ID");
                String TYPE = rs.getString("TYPE");
                String NAME = rs.getString("NAME");
                String RULE = rs.getString("RULE");
                String DESCRIBE = rs.getString("DESCRIBE");
                map.put(NAME, Integer.valueOf(RULE));

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return map;

    }

    /**
     * 根据保养产品名称查询最近一次的保养日期
     *
     * @param productName(产品名称)
     * @author zhangyibin
     */
    public static Map<String, String> onNearestdate(String productName) {
        Map<String, String> map = new HashMap<>();
        String sql = "select NAME, NEARESTDATE from (select NAME, NEARESTDATE from RECORD where NAME = '"
                + productName
                + "' order by NEARESTDATE desc) limit 1;";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String NAME = rs.getString("NAME");
                String NEARESTDATE = rs.getString("NEARESTDATE");
                map.put(NAME, NEARESTDATE);

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return map;

    }

    /**
     * 根据保养产品名称查询最近一次的保养里程
     *
     * @param productName(产品名称)
     * @author zhangyibin
     */
    public static Map<String, Integer> onMileage(String productName) {
        Map<String, Integer> map = new HashMap<>();
        String sql = "select NAME,MILEAGE ,NEARESTDATE from (select NAME,MILEAGE, NEARESTDATE from RECORD where NAME = '"
                + productName
                + "' order by NEARESTDATE desc) limit 1;";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String NAME = rs.getString("NAME");
                String MILEAGE = rs.getString("MILEAGE");
                map.put(NAME, Integer.valueOf(MILEAGE));

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return map;
    }
}
