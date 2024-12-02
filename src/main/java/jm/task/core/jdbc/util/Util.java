package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static String HOSTNAME = "localhost";
    private static String DBNAME = "usersdb";
    private static String USERNAME = "kata";
    private static String PASSWORD = "kata";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection connection;

    private Util() {

    }

    public static Connection getConnectionJDBC() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER);
        if (connection == null) {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:3306/%s", HOSTNAME, DBNAME),
                    USERNAME, PASSWORD);
            return connection;
        } else {
            return connection;
        }
    }

    public static void closeConnectionJDBC() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
