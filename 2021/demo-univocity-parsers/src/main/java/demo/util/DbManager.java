package demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DbManager {

    public static Connection getConnection() {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hosdb", "hosuser", "54321");
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection conn) {

        try {
            if (Objects.nonNull(conn)) {
                conn.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

}
