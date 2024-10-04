package com.sparta.schedule.db;
import com.sparta.schedule.entity.Schedule;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DB { // DB연동 클래스
    private static final String url = "jdbc:mysql://localhost:3306/schedule";
    private static final String id = "root";
    private static final String pass = "wjdgusdn07!";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, id, pass);
    }
    // 데이터 베이스에 연동 되어있음
    public void create(Schedule schedule) throws SQLException { //sql, DB연동
        String sql = "INSERT INTO schedules (username, contents) VALUES (?, ?)";
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, schedule.getUsername());
            pstmt.setString(2, schedule.getContents());
            pstmt.executeUpdate();
        }
    }
    public List<Schedule> read () throws SQLException { //sql, DB연동
        List<Schedule> scheduleList = new ArrayList<>();
        String sql = "SELECT * FROM schedules";
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Schedule schedule = new Schedule(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("contents")
                );
                scheduleList.add(schedule);
            }
        }
        return scheduleList;
    }
    public void update (Schedule schedule) throws SQLException { //sql, DB연동
        String sql = "UPDATE schedules SET contents = ?, username = ? WHERE id = ?";
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, schedule.getUsername());
            pstmt.setString(2, schedule.getContents());
            pstmt.executeUpdate();
        }
    }
    public void delete (Long id) throws SQLException { //sql, DB연동
        String sql = "DELETE FROM schedules WHERE id = ?";
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }
}
