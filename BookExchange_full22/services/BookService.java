package services;

import dao.BookDAO;
import models.Book;
import java.sql.SQLException;
import java.util.List;

public class BookService {

    private static BookDAO bookDAO = new BookDAO();

    public static void addBook(Book book) throws SQLException {
        bookDAO.addBook(book);
    }

    public static List<Book> getAllBooks() throws SQLException {
        return bookDAO.getAllBooks();
    }

    public static List<Book> searchBooks(String keyword) throws SQLException {
        return bookDAO.searchBooks(keyword);
    }

    public static List<Book> getBooksByOwner(int ownerId) throws SQLException {
        return bookDAO.getBooksByOwner(ownerId);
    }

    public static Book getBookById(int id) throws SQLException {
        return bookDAO.getBookById(id);
    }
}
