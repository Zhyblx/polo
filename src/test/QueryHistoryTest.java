package test;

import org.zhangyibin.service.QueryHistory;

public class QueryHistoryTest {

    public static void main(String[] args) throws Exception {
        QueryHistory queryHistory = new QueryHistory();
        queryHistory.setWriteFile("115666");
        System.out.println(queryHistory.getReadFile());

    }


}
