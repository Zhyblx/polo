package org.zhangyibin.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串转换数组
 *
 * @author zhangyibin
 */
public class StringConvertArray {

    public static String[][] getStringConvertArray(String json) {
        String[][] productTableArray = new String[9][4];

        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("return");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject productJson = new JSONObject(jsonArray.get(i).toString());
            productTableArray[i][0] = productJson.get("name").toString();
            productTableArray[i][1] = productJson.get("isChange").toString();
            productTableArray[i][2] = productJson.get("differenceNumber").toString();
            /*
             * 以下的单位定位是一种非规范的单位定义方式(即，临时方式)
             */
            if (i < 3) {
                productTableArray[i][3] = "天(超出天数)";
            } else {
                productTableArray[i][3] = "公里(超出公里数)";
            }

        }
        return productTableArray;

    }
}
