/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp2;

/**
 *
 * @author Student
 */

// Import the Login class
import com.mycompany.chatapp2.Login;

// Import the Message class
import com.mycompany.chatapp2.Message;

// Import Scanner for user input
import java.util.Scanner;

public class Main {

    // Main method where the program starts
    public static void main(String[] args) {

        // Create Scanner object to read user input
        Scanner input = new Scanner(System.in);

        // Create Login object to access login methods
        Login login = new Login();

        // =========================
        // USER REGISTRATION SECTION
        // =========================

        System.out.println("=== USER REGISTRATION ===");

        // Ask user to enter a username
        System.out.print("Enter a username: ");
        String username = input.nextLine();

        // Ask user to enter a password
        System.out.print("Enter a password: ");
        String password = input.nextLine();

        // Ask user to enter a South African phone number
        System.out.print("Enter your South African phone number (+27...): ");
        String phone = input.nextLine();

        // Register the user and store the returned message
        String response = login.registerUser(username, password, phone);

        // Display registration result
        System.out.println(response);

        // ====================
        // USER LOGIN SECTION
        // ====================

        System.out.println("\n=== USER LOGIN ===");

        // Ask user to enter username for login
        System.out.print("Enter your username: ");
        String loginUsername = input.nextLine();

        // Ask user to enter password for login
        System.out.print("Enter your password: ");
        String loginPassword = input.nextLine();

        // Check if login details are correct
        boolean loggedIn = login.loginUser(loginUsername, loginPassword);

        // Get login status message
        String loginMessage = login.returnLoginStatus(loggedIn);

        // Display login message
        System.out.println(loginMessage);

        // ====================
        // PART 2 - CHAT SYSTEM
        // ====================

        // Continue only if login is successful
        if (loggedIn) {

            // Display welcome message
            System.out.println("Welcome to ChatApp.");

            // Boolean variable used to control the menu loop
            boolean running = true;

            // Keep showing the menu until the user chooses to quit
            while (running) {

                // Display menu options
                System.out.println("\n1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Choose an option: ");

                // Read user's menu choice
                int choice = input.nextInt();

                // Clear the scanner buffer
                input.nextLine();

                // Handle menu choices
                switch (choice) {

                    // =====================
                    // OPTION 1: SEND MESSAGE
                    // =====================
                    case 1:

                        // Ask user how many messages they want to send
                        System.out.print("How many messages would you like to send? ");
                        int numMessages = input.nextInt();

                        // Clear scanner buffer
                        input.nextLine();

                        // Loop through each message
                        for (int i = 0; i < numMessages; i++) {

                            // Generate message number
                            int messageNumber = i + 1;

                            System.out.println("\n--- Message " + messageNumber + " ---");

                            // Ask for recipient phone number
                            System.out.print("Enter recipient cell number: ");
                            String recipient = input.nextLine();

                            // Ask user to type the message
                            System.out.print("Enter your message: ");
                            String messageText = input.nextLine();

                            // Create Message object
                            Message msg = new Message(recipient, messageText, messageNumber);

                            // Check if recipient number is valid
                            System.out.println(msg.checkRecipientCell());

                            // Check if message length is valid
                            String lengthCheck = msg.checkMessageLength();

                            // Display message length result
                            System.out.println(lengthCheck);

                            // Continue only if message passes validation
                            if (lengthCheck.equals("Message ready to send.")) {

                                // Store returned action message
                                String action = msg.sentMessage();

                                // Display action result
                                System.out.println(action);

                                // Display message details
                                System.out.println(msg.printMessages());
                            }
                        }

                        // Display total messages processed
                        System.out.println("Total messages processed: " + numMessages);

                        break;

                    // =====================================
                    // OPTION 2: SHOW RECENTLY SENT MESSAGES
                    // =====================================
                    case 2:

                        // Placeholder feature
                        System.out.println("Coming Soon.");

                        break;

                    // =====================
                    // OPTION 3: QUIT PROGRAM
                    // =====================
                    case 3:

                        // Stop the menu loop
                        running = false;

                        // Display goodbye message
                        System.out.println("Goodbye!");

                        break;

                    // =====================
                    // INVALID MENU OPTION
                    // =====================
                    default:

                        // Display error message for invalid choice
                        System.out.println("Invalid option. Please try again.");
                }
            }

        } else {

            // Display message if login fails
            System.out.println("Login failed. Exiting application.");
        }

        // Close scanner to prevent resource leaks
        input.close();
    }
}