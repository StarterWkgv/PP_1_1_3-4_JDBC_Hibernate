package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static String hostName = "localhost";
    private static String dbName = "usersdb";
    private static String userName = "kata";
    private static String password = "kata";

    private Util() {

    }

    public static Connection getConnection_JDBC() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(String.format("jdbc:mysql://%s:3306/%s", hostName, dbName),
                userName, password);
    }
}
