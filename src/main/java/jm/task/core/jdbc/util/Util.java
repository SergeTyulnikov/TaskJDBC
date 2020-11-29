package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?autoReconnect=true&useSSL=FALSE&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER_NAME = "Serge";
    private static final String PASSWORD = "Mercedes69@";

    private Connection connection;

    public Util() {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Fuck the connection!");
        }
    }

    public Connection getConnection() {
        return connection;
    }



}
