package models;

public class User {
    private int id;
    private String name, email, password, department;
    private int year;

    // No-argument constructor
    public User() { }

    // Constructor with all fields
    public User(int id, String name, String email, String password, String department, int year) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.department = department;
        this.year = year;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    // Overload to match callers that pass a String year
    public void setYear(String yearStr) {
        if (yearStr == null) {
            this.year = 0;
            return;
        }
        try {
            this.year = Integer.parseInt(yearStr.trim());
        } catch (NumberFormatException e) {
            this.year = 0; // fallback if input isn't numeric
        }
    }
}
