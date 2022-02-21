package org.zhangyibin.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库工具类<br>
 * <p>
 * 描述：作用于链接数据库；
 *
 * @author zhangyibin
 */
public class DBUtil {
    static final String database = "./db/polo.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
    }

    public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:sqlite:%s", database);
        return DriverManager.getConnection(url);

    }
}