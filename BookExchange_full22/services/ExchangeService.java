package services;

import dao.BookDAO;
import dao.RequestDAO;
import dao.UserDAO;
import java.sql.SQLException;
import java.util.List;
import models.Book;
import models.Request;
import models.User;

public class ExchangeService {
    private UserDAO userDAO = new UserDAO();
    private BookDAO bookDAO = new BookDAO();
    private RequestDAO requestDAO = new RequestDAO();

    public void registerUser(User user) throws SQLException {
        userDAO.registerUser(user);
    }

    public User login(String email, String password) throws SQLException {
        return userDAO.login(email, password);
    }

    public void addBook(Book book) throws SQLException {
        bookDAO.addBook(book);
    }

    public List<Book> searchBooks(String keyword) throws SQLException {
        return bookDAO.searchBooks(keyword);
    }

    public List<Book> getMyBooks(int ownerId) throws SQLException {
        return bookDAO.getBooksByOwner(ownerId);
    }

    public void sendExchangeRequest(int senderId, int bookNeededId, int bookOfferId) throws SQLException {
        // Find owner of needed book
        Book needed = bookDAO.getBookById(bookNeededId);
        if (needed == null) {
            System.out.println("No such book found.");
            return;
        }
        int receiverId = needed.getOwnerId();
        Request req = new Request();
        req.setSenderId(senderId);
        req.setReceiverId(receiverId);
        req.setBookRequested(bookNeededId);
        req.setBookOffered(bookOfferId);
        req.setStatus("Pending");
        requestDAO.sendRequest(req);
    }

    public List<Request> getIncomingRequests(int userId) throws SQLException {
        return requestDAO.getIncomingRequests(userId);
    }

    public List<Request> getOutgoingRequests(int userId) throws SQLException {
        return requestDAO.getOutgoingRequests(userId);
    }

    // ✅ Accept request
    public void acceptRequest(int requestId) throws SQLException {
        // Update status in DB
        requestDAO.acceptRequest(requestId);

        // Optional: update books availability
        // You could fetch the request and mark exchanged books as unavailable
        // Example:
        // Request req = requestDAO.getRequestById(requestId);
        // if (req != null) {
        //     bookDAO.markAsExchanged(req.getBookRequested());
        //     bookDAO.markAsExchanged(req.getBookOffered());
        // }
    }

    // ✅ Reject request
    public void rejectRequest(int requestId) throws SQLException {
        requestDAO.rejectRequest(requestId);
    }
}
