# Chat Application - Part 2 & 3

---

## Student Information
- Name: Entle
- Student Number: ST10538239
- Module: PROGR5121

---

## Project Information
This project is a simple user authentication system built using Java. It includes features for user registration, password validation, and login functionality, with unit tests to verify the correctness of these features. The project is designed for learning purposes and is suitable for beginners

---

## Features
- User Registration: Allows a user to register with a username, password, and phone number.
- Username Validation: Ensures the username is correctly formatted (length ≤ 5 and contains an underscore).
- Password Validation: Enforces password complexity requirements (at least 8 characters, one uppercase letter, one digit, and one special character).
- Phone Number Validation: Checks if the phone number starts with +27 and is exactly 12 characters long.
- Login: Allows a registered user to log in using their username and password.

---

# Test Cases
- The following test cases are covered in the project:
## Username Validation
- Valid username (contains an underscore and has a length ≤ 5)
- Invalid username (no underscore)
- Invalid username (length > 5)

## Password Validation
- Valid password (at least 8 characters, contains an uppercase letter, a digit, and a special character)
- Invalid password (no uppercase letter)
- Invalid password (no number)
- Invalid password (no special character)
- Invalid password (too short)

## Phone Number Validation
- Valid phone number (starts with +27 and is exactly 12 characters long)
- Invalid phone number (wrong format)

## Registration
- Successful registration (when all inputs are valid)
- Failed registration (when phone number is invalid)

## Login
- Successful login (when username and password match)
- Failed login (when username or password is incorrect)

---

# Example Usage
## User Registration
- The user is prompted to enter:
- If the input is valid, the user will be registered successfully.
    - A valid username (e.g., ky_1)
    - A valid password (e.g., Ch&&sec@ke99)
    - A valid South African phone number (e.g., +27837719808)
      
## User Login
- After successful registration, the user can log in by providing:
- The system will authenticate the credentials and provide feedback on the success or failure of the login attempt.
The username (e.g., ky_1)
The password (e.g., Ch&&sec@ke99) 
