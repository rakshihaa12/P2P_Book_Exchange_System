package dao;

import models.User;
import java.sql.*;

public class UserDAO {

    // Register a new user
    public void registerUser(User user) throws SQLException {
        String sql = "INSERT INTO users (name, email, password, department, year) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getDepartment());
            pstmt.setInt(5, user.getYear());
            pstmt.executeUpdate();
        }
    }

    // Login user
    public User login(String email, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE email=? AND password=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setDepartment(rs.getString("department"));
                u.setYear(rs.getInt("year")); // keep as int
                return u;
            } else {
                return null;
            }
        }
    }
}
