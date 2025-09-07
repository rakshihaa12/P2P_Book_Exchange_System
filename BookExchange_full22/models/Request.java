package models;

public class Request {
    private int id;
    private int senderId, receiverId, bookRequested, bookOffered;
    private String status;

    // No-argument constructor
    public Request() { }

    // Constructor with all fields except id (for new requests)
    public Request(int senderId, int receiverId, int bookRequested, int bookOffered, String status) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.bookRequested = bookRequested;
        this.bookOffered = bookOffered;
        this.status = status;
    }

    // Constructor including id (for reading from database)
    public Request(int id, int senderId, int receiverId, int bookRequested, int bookOffered, String status) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.bookRequested = bookRequested;
        this.bookOffered = bookOffered;
        this.status = status;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getSenderId() { return senderId; }
    public void setSenderId(int senderId) { this.senderId = senderId; }

    public int getReceiverId() { return receiverId; }
    public void setReceiverId(int receiverId) { this.receiverId = receiverId; }

    public int getBookRequested() { return bookRequested; }
    public void setBookRequested(int bookRequested) { this.bookRequested = bookRequested; }

    public int getBookOffered() { return bookOffered; }
    public void setBookOffered(int bookOffered) { this.bookOffered = bookOffered; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
