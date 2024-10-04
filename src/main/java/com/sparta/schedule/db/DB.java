package com.sparta.schedule.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DB { // DB연동 클래스
    private static final String url = "jdbc:mysql://localhost:3306/schedule";
    private static final String id = "root";
    private static final String pass = "wjdgusdn07!";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, id, pass);
    }

}
