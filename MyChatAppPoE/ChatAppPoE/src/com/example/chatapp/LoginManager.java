package com.example.chatapp;

public class LoginManager {

    private user savedUser;

    // Validate username
    public boolean validateUsername(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    // Validate password complexity
    public boolean validatePassword(String password) {

        if (password == null || password.length() < 8) {
            return false;
        }

        boolean containsUppercase = false;
        boolean containsNumber = false;
        boolean containsSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char character = password.charAt(i);

            if (Character.isUpperCase(character)) {
                containsUppercase = true;
            } else if (Character.isDigit(character)) {
                containsNumber = true;
            } else if (!Character.isLetterOrDigit(character)) {
                containsSpecial = true;
            }
        }

        return containsUppercase && containsNumber && containsSpecial;
    }

    // Validate cellphone
    public boolean validatePhoneNumber(String cellphone) {
        return cellphone != null && cellphone.matches("^\\+27\\d{9}$");
    }

    // Register account
    public String registerAccount(String username, String password, String cellphone) {

        if (!validateUsername(username)) {
            return "Invalid username. Username must contain an underscore and be 5 characters or less.";
        }

        if (!validatePassword(password)) {
            return "Invalid password. Password must be at least 8 characters and include uppercase, number, and special symbol.";
        }

        if (!validatePhoneNumber(cellphone)) {
            return "Invalid cellphone number. Please use South African international format.";
        }

        savedUser = new user(username, password, cellphone);
        return "User registered successfully.";
    }

    // Login verification
    public boolean verifyLogin(String username, String password) {

        if (savedUser == null) {
            return false;
        }

        return savedUser.getUsername().equals(username)
                && savedUser.getPassword().equals(password);
    }

    // Login status message
    public String getLoginMessage(boolean success, String username) {

        if (!success) {
            return "Login failed. Incorrect username or password.";
        }

        String[] userParts = username.split("_");

        String firstName = userParts.length > 0 ? userParts[0] : "";
        String secondPart = userParts.length > 1 ? userParts[1] : "";

        return "Hello " + firstName + " " + secondPart + ", welcome back!";
    }
}