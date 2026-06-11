package com.mycompany.chatapp2;

import java.util.Scanner;

/**
 * Main class - ChatApp entry point
 * @author Student
 */
public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Login login = new Login();

        String username = "";
        String password = "";
        String phone = "";
        String firstName = "";
        String lastName = "";
        // =========================
        // USER REGISTRATION SECTION
        // =========================

        System.out.println("=== USER REGISTRATION ===");
        
        System.out.print("First Name: ");
        firstName = input.nextLine();
        
        System.out.print("Last Name: ");
        lastName = input.nextLine();
        
        // =====================
        // USERNAME VALIDATION LOOP
        // =====================
        while (true) {
            System.out.print("Enter a username: ");
            username = input.nextLine();

            boolean validUsername = login.checkUsername(username);

            if (validUsername) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Username is not correctly formatted.");
            }
        }

        // =====================
        // PASSWORD VALIDATION LOOP
        // =====================
        while (true) {
            System.out.print("Enter a password: ");
            password = input.nextLine();

            boolean validPassword = login.checkPasswordComplexity(password);

            if (validPassword) {
                System.out.println("Password successfully captured.");
                break;
            } else {
                System.out.println("Password does not meet complexity requirements.");
            }
        }

        // =====================
        // PHONE NUMBER VALIDATION LOOP
        // =====================
        while (true) {
            System.out.print("Enter your South African phone number (+27...): ");
            phone = input.nextLine();

            boolean validPhone = login.checkCellPhoneNumber(phone);

            if (validPhone) {
                System.out.println("Cell phone number successfully added.");
                break;
            } else {
                System.out.println("Phone number is incorrectly formatted.");
            }
        }

        // =====================
        // STORE USER DETAILS
        // =====================
        login.registerUser(username, password, phone);

        // ====================
        // USER LOGIN SECTION
        // ====================

        System.out.println("\n=== USER LOGIN ===");

        boolean loggedIn = false;

        while (!loggedIn) {

            System.out.print("Enter your username: ");
            String loginUsername = input.nextLine();

            System.out.print("Enter your password: ");
            String loginPassword = input.nextLine();

            loggedIn = login.loginUser(loginUsername, loginPassword);

            String loginMessage = login.returnLoginStatus(loggedIn);
            System.out.println(loginMessage);

            if (!loggedIn) {
                System.out.println("Incorrect username or password. Please try again.\n");
            }
        }

        // ====================
        // PART 2 - CHAT SYSTEM
        // ====================

        if (loggedIn) {

            System.out.println("Welcome to ChatApp.");

            boolean running = true;

            while (running) {

                System.out.println("\n1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.println("4) Stored Messages");
                System.out.print("Choose an option: ");

                int choice = input.nextInt();
                input.nextLine(); // clear buffer

                switch (choice) {

                    // =====================
                    // OPTION 1: SEND MESSAGE
                    // =====================
                    case 1:

                        System.out.print("How many messages would you like to send? ");
                        int numMessages = input.nextInt();
                        input.nextLine();

                        for (int i = 0; i < numMessages; i++) {

                            int messageNumber = i + 1;

                            System.out.println("\n--- Message " + messageNumber + " ---");

                            String recipient = "";
                            boolean validRecipient = false;

                            while (!validRecipient) {
                                System.out.print("Enter recipient cell number: ");
                                recipient = input.nextLine();

                                // Validate using same logic as Message.checkRecipientCell()
                                if (recipient.startsWith("+27") && recipient.length() <= 13) {
                                    System.out.println("Cell phone number successfully captured.");
                                    validRecipient = true;
                                } else {
                                    System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                                }
                            }
                            
                            System.out.print("Enter your message: ");
                            String messageText = input.nextLine();

                            Message msg = new Message(recipient, messageText, messageNumber);

                            System.out.println(msg.checkRecipientCell());

                            String lengthCheck = msg.checkMessageLength();
                            System.out.println(lengthCheck);

                            if (lengthCheck.equals("Message ready to send.")) {

                                String action = msg.sentMessage(input);
                                System.out.println(action);

                            }
                        }

                        System.out.println("Total messages processed: " + numMessages);

                        break;

                    // =====================================
                    // OPTION 2: SHOW RECENTLY SENT MESSAGES
                    // =====================================
                    case 2:

                        System.out.println("\n=== Recently Sent Messages ===");

                        
                        if (!Message.sentMessages.isEmpty()) {

                            for (int i = 0; i < Message.sentMessages.size(); i++) {

                                System.out.println("Recipient: "
                                        + Message.recipients.get(i));

                                System.out.println("Hash: "
                                        + Message.messageHashes.get(i));

                                System.out.println("Message: "
                                        + Message.sentMessages.get(i));

                                System.out.println("-------------------------");
                            }

                        } else {

                            System.out.println("No messages have been sent.");
                        }
                        break;

                    // =====================
                    // OPTION 3: QUIT PROGRAM
                    // =====================
                    case 3:

                        running = false;
                        System.out.println("Goodbye!");

                        break;
                    case 4:

                        // Load stored messages from file before showing submenu
                        Message.loadStoredMessages();
                        boolean subMenu = true;
                        
                        while (subMenu) {

                            System.out.println("\n===== Stored Messages =====");
                            System.out.println("a) Display all stored messages");
                            System.out.println("b) Display longest message");
                            System.out.println("c) Search by message ID");
                            System.out.println("d) Search by recipient");
                            System.out.println("e) Delete by message hash");
                            System.out.println("f) Display full report");
                            System.out.println("q) Return");
                            System.out.print("choose option(a,b,c,d,e,f or q; all must be in lower case text): ");
                            String subChoice = input.nextLine().trim().toLowerCase();

                            switch (subChoice) {
                                
                                case "a":
                                    System.out.println("======= All Stored Messages ==========");

                                    for (int i = 0; i < Message.storedMessages.size(); i++) {

                                        String rec = i < Message.recipients.size() ? Message.recipients.get(i) : "";
                                        String hash = i < Message.messageHashes.size() ? Message.messageHashes.get(i) : "";
                                        String id = i < Message.messageIDs.size() ? Message.messageIDs.get(i) : "";

                                        System.out.println("Recipient: " + rec);
                                        System.out.println("Hash: " + hash);
                                        System.out.println("ID: " + id);
                                        System.out.println("Message: " + Message.storedMessages.get(i));
                                        System.out.println("-------------------------");
                                    }

                                    break;

                                case "b":
                                    System.out.println("======= Longest Stored Message ==========");
                                    System.out.println(Message.displayLongestMessage());

                                    break;

                                case "c":

                                    System.out.print(
                                            "Enter Message ID: ");

                                    String id = input.nextLine();

                                    System.out.println(
                                            Message.searchByMessageID(id));

                                    break;

                                case "d":

                                    System.out.print(
                                            "Enter Recipient: ");

                                    String recipient =
                                            input.nextLine();

                                    System.out.println(
                                            Message.searchByRecipient(
                                                    recipient));

                                    break;

                                case "e":

                                    System.out.print(
                                            "Enter Hash: ");

                                    String hash =
                                            input.nextLine();

                                    System.out.println(
                                            Message.deleteByHash(hash));

                                    break;

                                case "f":

                                    System.out.println(
                                            Message.printMessagesReport());

                                    break;

                                case "q":

                                    subMenu = false;

                                    break;

                                default:

                                    System.out.println(
                                            "Invalid option.");
                            }
                        }
                        break;


                    
                    // =====================
                    // INVALID MENU OPTION
                    // =====================
                    default:

                        System.out.println("Invalid option. Please try again.");
                }
            }

        } else {

            System.out.println("Login failed. Exiting application.");
        }

        input.close();
    }
}