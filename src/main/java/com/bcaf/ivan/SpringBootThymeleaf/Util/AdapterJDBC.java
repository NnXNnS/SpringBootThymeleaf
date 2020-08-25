package com.bcaf.ivan.SpringBootThymeleaf.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdapterJDBC {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/hacktiv8_bcaf?serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "";
    private static Connection conn;

    public static Connection getConnection() {
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            if (conn == null) {
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Connected to mysql server successfull");
            }
        } catch (SQLException e) {
            System.out.println("connection error");
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
