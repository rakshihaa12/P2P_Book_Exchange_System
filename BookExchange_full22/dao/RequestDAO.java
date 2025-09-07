package dao;

import models.Request;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO {

    // Send a new request
    public void sendRequest(Request request) throws SQLException {
        String sql = "INSERT INTO requests (senderId, receiverId, bookRequested, bookOffered, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, request.getSenderId());
            pstmt.setInt(2, request.getReceiverId());
            pstmt.setInt(3, request.getBookRequested());
            pstmt.setInt(4, request.getBookOffered());
            pstmt.setString(5, request.getStatus());
            pstmt.executeUpdate();
        }
    }

    // Get incoming requests for a user
    public List<Request> getIncomingRequests(int userId) throws SQLException {
        List<Request> requests = new ArrayList<>();
        String sql = "SELECT * FROM requests WHERE receiverId=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Request r = new Request();
                r.setId(rs.getInt("id"));
                r.setSenderId(rs.getInt("senderId"));
                r.setReceiverId(rs.getInt("receiverId"));
                r.setBookRequested(rs.getInt("bookRequested"));
                r.setBookOffered(rs.getInt("bookOffered"));
                r.setStatus(rs.getString("status"));
                requests.add(r);
            }
        }
        return requests;
    }

    // Get outgoing requests for a user
    public List<Request> getOutgoingRequests(int userId) throws SQLException {
        List<Request> requests = new ArrayList<>();
        String sql = "SELECT * FROM requests WHERE senderId=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Request r = new Request();
                r.setId(rs.getInt("id"));
                r.setSenderId(rs.getInt("senderId"));
                r.setReceiverId(rs.getInt("receiverId"));
                r.setBookRequested(rs.getInt("bookRequested"));
                r.setBookOffered(rs.getInt("bookOffered"));
                r.setStatus(rs.getString("status"));
                requests.add(r);
            }
        }
        return requests;
    }

    // Accept a request
    public void acceptRequest(int requestId) throws SQLException {
        String sql = "UPDATE requests SET status='Accepted' WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, requestId);
            pstmt.executeUpdate();
        }
    }

    // Reject a request
    public void rejectRequest(int requestId) throws SQLException {
        String sql = "UPDATE requests SET status='Rejected' WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, requestId);
            pstmt.executeUpdate();
        }
    }
}
