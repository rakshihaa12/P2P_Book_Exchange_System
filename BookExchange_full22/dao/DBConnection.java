package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Update these credentials to match your MySQL setup
    private static final String URL = "jdbc:mysql://localhost:3306/book_exchange";
    private static final String USER = "root";
    private static final String PASSWORD = "Anithapk12#";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
