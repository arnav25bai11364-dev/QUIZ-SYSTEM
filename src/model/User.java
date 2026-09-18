package model;

public abstract class User {
    protected int id;
    protected String username;
    protected String password;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public boolean checkPassword(String password) { return this.password.equals(password); }

    public abstract String getRole();
}
