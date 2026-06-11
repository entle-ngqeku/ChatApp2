/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp2;

/**
 *
 *
 * @author Student
 */
public class Login {

    private String storedUsername;   
    private String storedPassword;
    @SuppressWarnings("unused")
    private String storedPhoneNumber;

    //-------------------------
    // USERNAME VALIDATION
    //-------------------------
    public boolean checkUsername(String username) {
        return username != null &&
               username.length() <= 5 &&
               username.contains("_");
    }

    //-------------------------
    // PASSWORD VALIDATION
    //-------------------------
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasNumber = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }

        return hasUppercase && hasNumber && hasSpecialChar;
    }

    //-------------------------
    // PHONE NUMBER VALIDATION
    //-------------------------
    public boolean checkCellPhoneNumber(String phoneNumber) {
        // Must start with +27 and be 12 characters long
        return phoneNumber != null &&
               phoneNumber.matches("^\\+27\\d{9}$");
    }

    //-------------------------
    // REGISTER USER
    //-------------------------
    public String registerUser(String username, String password, String phoneNumber) {

        if (!checkUsername(username)) {
            return "Username is not correctly formatted.";
        }
        // Password
        if (!checkPasswordComplexity(password)) {
            return "Password does not meet complexity requirements.";
        }
        // Phone number
        if (!checkCellPhoneNumber(phoneNumber)) {
            return "Phone number is incorrectly formatted.";
        }
        // If all validations pass
        else {
            // Store user details
            storedUsername = username;
            storedPassword = password;
            storedPhoneNumber = phoneNumber;
            System.out.println("User registered successfully.");
        }
        
        return "Registration complete.";
    }

    //-------------------------
    // LOGIN USER
    //-------------------------
    public boolean loginUser(String username, String password) {
        return username != null && password != null &&
               username.equals(storedUsername) &&
               password.equals(storedPassword);
    }

    //-------------------------
    // LOGIN STATUS MESSAGE
    //-------------------------
    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + storedUsername + ", it is great to see you again.";
        } else {
            return "Login failed. Username or password is incorrect.";
        }
    }
}
