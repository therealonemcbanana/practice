package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JDBCUtils {private static final ResourceBundle reader = ResourceBundle.getBundle("dbconfig");

    public static Connection getConnection() throws SQLException {
        String url = reader.getString("db.url");
        String user = reader.getString("db.username");
        String password = reader.getString("db.password");

        return DriverManager.getConnection(url, user, password);
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
