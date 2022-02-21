package org.zhangyibin.service;

import java.io.*;

/**
 * 缓存和查询上一次的里程数据
 *
 * @author 张益斌
 */

public class QueryHistory {
    File file = new File("db/queryHistory.txt");

    /**
     * 缓存上次查询的里程数据
     *
     * @param query(里程数据)
     */
    public void setWriteFile(String query) {
        try {
            OutputStream outputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(query);
            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * 返回上次查询的里程数据
     *
     * @return
     */
    public String getReadFile() {
        String query = "";
        try {
            InputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                query = line;

            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return query;

    }
}
