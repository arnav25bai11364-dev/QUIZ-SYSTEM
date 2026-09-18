package service;

import model.Admin;
import model.Student;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private final Map<String, User> users = new HashMap<>();

    public AuthService() {
        users.put("student", new Student(1, "student", "1234"));
        users.put("admin", new Admin(2, "admin", "admin123"));
    }

    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.checkPassword(password)) {
            return user;
        }
        return null;
    }

    public boolean registerStudent(String username, String password) {
        if (users.containsKey(username) || username.isBlank() || password.isBlank()) {
            return false;
        }

        users.put(username, new Student(users.size() + 1, username, password));
        return true;
    }
}
