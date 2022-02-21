package org.zhangyibin.maintenanceitem;

import org.zhangyibin.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 输入保养记录
 *
 * @author zhangyibin
 */
public class EnterMaintenanceRecords {

    /*
     * 获取数据库主键ID
     *
     */
    private int getId() {
        int ID = 0;
        String sql = "select ID from (select ID from RECORD order by ID desc) limit 1;";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ID = rs.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return ID;

    }

    /**
     * 输入保养记录
     *
     * @param describe(描述)
     * @param productParameter(所保养的产品名称)
     * @param time(保养时间)
     * @param mileage(保养里程)
     * @return (true/false)
     * @author zhangyibin
     */
    public Boolean onEnterMaintenanceRecords(String describe, String productParameter, String time, int mileage) {

        String sql = "insert into RECORD (`DESCRIBE`,`MILEAGE`,`NAME`,`NEARESTDATE`,`ID`) values (?,?,?,?,?);";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, describe);
            ps.setInt(2, mileage);
            ps.setString(3, productParameter);
            ps.setString(4, time);
            ps.setInt(5, getId() + 1); // 通过数据库中最大的键ID+1的方式实现插入数据时，主键自动增加。
            ps.execute();
        } catch (SQLException e) {
            return false;

        }
        return true;
    }
}
