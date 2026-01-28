package com.revplay.util.service;



import com.revplay.util.dao.UserDAO;
import com.revplay.util.model.User;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    // Register user with validation
    public boolean registerUser(String name, String email, String password) {

        if (name == null || name.isEmpty()) {
            System.out.println("Name cannot be empty");
            return false;
        }
        if (email == null || !email.contains("@")) {
            System.out.println("Invalid email");
            return false;
        }
        if (password == null || password.length() < 4) {
            System.out.println("Password must be at least 4 characters");
            return false;
        }

        User user = new User(name, email, password);
        return userDAO.registerUser(user);
    }

    // Login user
    public User loginUser(String email, String password) {

        if (email == null || password == null) {
            System.out.println("Email and password required");
            return null;
        }
        return userDAO.loginUser(email, password);
    }
}

