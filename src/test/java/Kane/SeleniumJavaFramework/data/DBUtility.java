package Kane.SeleniumJavaFramework.data;

import java.sql.*;
import java.util.*;

public class DBUtility {

    public static List<HashMap<String, String>> getTestData(String url, String user, String password, String query) {
        List<HashMap<String, String>> dataList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();

            while (rs.next()) {
                HashMap<String, String> row = new HashMap<>();
                for (int i = 1; i <= cols; i++) {
                    String colName = meta.getColumnLabel(i); // column alias-safe
                    String value = rs.getString(i);
                    row.put(colName, value);
                }
                dataList.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException("DB query failed: " + e.getMessage(), e);
        }
        return dataList;
    }
}
