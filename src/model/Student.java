package model;

public class Student extends User {
    public Student(int id, String username, String password) {
        super(id, username, password);
    }

    @Override
    public String getRole() {
        return "STUDENT";
    }
}
