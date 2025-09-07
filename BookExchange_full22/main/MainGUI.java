package main;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import models.*;
import services.*;

public class MainGUI extends JFrame {

    private User currentUser = null;

    public MainGUI() {
        setTitle("P2P Book Exchange");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        showMainMenu();
    }

    /** MAIN MENU **/
    private void showMainMenu() {
        getContentPane().removeAll();

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton registerBtn = new JButton("Register");
        JButton loginBtn = new JButton("Login");
        JButton exitBtn = new JButton("Exit");

        panel.add(registerBtn);
        panel.add(loginBtn);
        panel.add(exitBtn);

        add(panel);
        revalidate();
        repaint();

        registerBtn.addActionListener(e -> showRegisterForm());
        loginBtn.addActionListener(e -> showLoginForm());
        exitBtn.addActionListener(e -> System.exit(0));
    }

    /** REGISTER PAGE **/
    private void showRegisterForm() {
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField deptField = new JTextField();
        JTextField yearField = new JTextField();

        Object[] fields = {
            "Name:", nameField,
            "Email:", emailField,
            "Password:", passwordField,
            "Department:", deptField,
            "Year:", yearField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Register User", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int year = Integer.parseInt(yearField.getText());
                User user = new User(0, nameField.getText(), emailField.getText(),
                        new String(passwordField.getPassword()),
                        deptField.getText(),
                        year);
                UserService.registerUser(user);
                JOptionPane.showMessageDialog(this, "User registered successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    /** LOGIN PAGE **/
    private void showLoginForm() {
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        Object[] fields = {
            "Email:", emailField,
            "Password:", passwordField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                User user = UserService.login(emailField.getText(), new String(passwordField.getPassword()));
                if (user != null) {
                    currentUser = user;
                    JOptionPane.showMessageDialog(this, "Welcome, " + currentUser.getName());
                    showDashboard();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    /** DASHBOARD **/
    private void showDashboard() {
        getContentPane().removeAll();

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        JButton viewBooksBtn = new JButton("View Books");
        JButton addBookBtn = new JButton("Add Book");
        JButton requestBookBtn = new JButton("Request Book");
        JButton viewRequestsBtn = new JButton("View Requests");
        JButton logoutBtn = new JButton("Logout");

        panel.add(viewBooksBtn);
        panel.add(addBookBtn);
        panel.add(requestBookBtn);
        panel.add(viewRequestsBtn);
        panel.add(logoutBtn);

        add(panel);
        revalidate();
        repaint();

        viewBooksBtn.addActionListener(e -> showBooksPage());
        addBookBtn.addActionListener(e -> showAddBookPage());
        requestBookBtn.addActionListener(e -> showRequestBookPage());
        viewRequestsBtn.addActionListener(e -> showViewRequestsPage());
        logoutBtn.addActionListener(e -> {
            currentUser = null;
            showMainMenu();
        });
    }

    /** ADD BOOK **/
    private void showAddBookPage() {
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField conditionField = new JTextField();

        Object[] fields = {
            "Title:", titleField,
            "Author:", authorField,
            "Category:", categoryField,
            "Condition:", conditionField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Add Book", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                Book book = new Book(0, titleField.getText(), authorField.getText(),
                        categoryField.getText(), conditionField.getText(),
                        currentUser.getId());
                BookService.addBook(book);
                JOptionPane.showMessageDialog(this, "Book added successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    /** VIEW BOOKS **/
    private void showBooksPage() {
        try {
            List<Book> books = BookService.getAllBooks();
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (Book b : books) {
                listModel.addElement(b.getId() + ". " + b.getTitle() + " by " + b.getAuthor());
            }
            JList<String> bookList = new JList<>(listModel);
            JScrollPane scrollPane = new JScrollPane(bookList);

            JOptionPane.showMessageDialog(this, scrollPane, "Available Books", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    /** REQUEST BOOK **/
    private void showRequestBookPage() {
        JTextField bookIdField = new JTextField();
        JTextField offerBookIdField = new JTextField();

        Object[] fields = {
            "Book ID to request:", bookIdField,
            "Your Book ID to offer:", offerBookIdField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Request Book", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int bookRequested = Integer.parseInt(bookIdField.getText());
                int bookOffered = Integer.parseInt(offerBookIdField.getText());

                RequestService.sendRequest(currentUser.getId(), bookRequested, bookOffered);
                JOptionPane.showMessageDialog(this, "Request sent!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    /** VIEW REQUESTS **/
    private void showViewRequestsPage() {
        try {
            List<Request> requests = RequestService.getRequestsForUser(currentUser.getId());
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (Request r : requests) {
                listModel.addElement("Request #" + r.getId() + " | Status: " + r.getStatus());
            }
            JList<String> requestList = new JList<>(listModel);
            JScrollPane scrollPane = new JScrollPane(requestList);

            int option = JOptionPane.showConfirmDialog(
                this,
                scrollPane,
                "Requests",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
            );

            // If user selects OK, let them Accept or Reject
            if (option == JOptionPane.OK_OPTION && !requestList.isSelectionEmpty()) {
                String selected = requestList.getSelectedValue();
                if (selected != null) {
                    int requestId = Integer.parseInt(selected.split("#")[1].split("\\|")[0].trim());

                    Object[] actions = {"Accept", "Reject", "Cancel"};
                    int choice = JOptionPane.showOptionDialog(
                            this,
                            "Do you want to accept or reject Request #" + requestId + "?",
                            "Handle Request",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            actions,
                            actions[2]
                    );

                    if (choice == 0) { // Accept
                        RequestService.acceptRequest(requestId);
                        JOptionPane.showMessageDialog(this, "Request accepted!");
                    } else if (choice == 1) { // Reject
                        RequestService.rejectRequest(requestId);
                        JOptionPane.showMessageDialog(this, "Request rejected!");
                    }
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGUI gui = new MainGUI();
            gui.setVisible(true);
        });
    }
}
