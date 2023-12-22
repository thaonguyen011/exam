package com.example.poductmanagement.model.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/product_management";
    private static final String jdbcUsername = "root";
    private static final String jdbcPassword = "Modicung2486!";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("connect db success");
        return connection;
    }

}
