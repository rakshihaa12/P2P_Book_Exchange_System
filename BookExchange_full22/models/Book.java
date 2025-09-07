package models;

public class Book {
    private int id;
    private String title, author, edition, category, condition;
    private int ownerId;
    private boolean available = true;

    // No-argument constructor
    public Book() { }

    // New constructor to match MainGUI
    public Book(int id, String title, String author, String category, String condition, int ownerId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.condition = condition;
        this.ownerId = ownerId;
        this.available = true;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getEdition() { return edition; }
    public void setEdition(String edition) { this.edition = edition; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    public int getOwnerId() { return ownerId; }
    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
