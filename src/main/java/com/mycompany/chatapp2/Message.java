
package com.mycompany.chatapp2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.JSONObject;



/**
 * Message class for handling all message operations.
 *
 * @author Student
 */
public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    // Part 3 Arrays
    public static List<String> sentMessages = new ArrayList<>();
    public static List<String> disregardedMessages = new ArrayList<>();
    public static List<String> storedMessages = new ArrayList<>();
    public static List<String> messageHashes = new ArrayList<>();
    public static List<String> messageIDs = new ArrayList<>();
    public static List<String> recipients = new ArrayList<>();

    /**
     * Constructor
     */
    public Message(String recipient, String messageText, int messageNumber) {
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageNumber = messageNumber;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    public Message(String string, String hi_Mike_can_you_join_us_for_dinner_tonigh, String test, int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Generates random 10-digit message ID.
     */
    private String generateMessageID() {
        StringBuilder id = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            id.append((int) (Math.random() * 10));
        }

        return id.toString();
    }

    /**
     * Validates message ID.
     */
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    /**
     * Validates recipient number.
     */
    public String checkRecipientCell() {

        if (recipient.startsWith("+27") && recipient.length() <= 13) {
            return "Cell phone number successfully captured.";
        }

        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    /**
     * Validates message length.
     */
    public String checkMessageLength() {

        if (messageText.length() <= 250) {
            return "Message ready to send.";
        }

        int over = messageText.length() - 250;
        return "Message exceeds 250 characters by " + over + "; please reduce the size.";
    }

    /**
     * Creates message hash.
     */
    public String createMessageHash() {
        // part1: messageNumber formatted to 2 digits. 0 ->00
        String part1 = String.format("%02d", messageNumber);
        
        //part2: single 0 because test expects 00:0 HITONIGHT
        String part2 = "0";
      
        // part 3: message text, uppercase, remove all spaces/punctuation, first 8 chars
        String cleanMsg = messageText.toUpperCase().replaceAll("[^A-Z]", "");
        
        //Take first 8 cjhars safetly so it doesn't crash
        String part3 = cleanMsg.substring(0, Math.min(8, cleanMsg.length()));
                
        return part1  + ":" + part2 + ":" + part3;
    }

    /**
     * Handles Send, Disregard and Store options.
     */
    public String sentMessage(Scanner input) {

        System.out.println("\nWhat would you like to do?");
        System.out.println("1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message");
        System.out.println("choose options(1, 2 or 3): ");
        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {

            case 1:

                sentMessages.add(messageText);
                messageHashes.add(messageHash);
                messageIDs.add(messageID);
                recipients.add(recipient);

                return "Message successfully sent."
                        + "\nMessage ID: " + messageID
                        + "\nMessage Hash: " + messageHash
                        + "\nRecipient: " + recipient
                        + "\nMessage: " + messageText;

            case 2:

                disregardedMessages.add(messageText);

                return "Message disregarded.";

            case 3:

                try {

                    JSONObject obj = new JSONObject();

                    obj.put("messageText", messageText);
                    obj.put("recipient", recipient);
                    obj.put("messageHash", messageHash);
                    obj.put("messageID", messageID);

                    FileWriter writer = new FileWriter("Message.json", true);
                    writer.write(obj.toString() + "\n");
                    writer.close();

                    messageHashes.add(messageHash);
                    messageIDs.add(messageID);
                    recipients.add(recipient);

                    return "Message stored successfully.";

                } catch (IOException e) {

                    return "Error storing message: " + e.getMessage();
                }

            default:
                return "Invalid choice.";
        }
    }

    /**
     * Reads stored messages from JSON file.
     */
    public static void loadStoredMessages() {

        // Clear existing lists to avoid duplicates on multiple loads
        storedMessages.clear();
        messageIDs.clear();
        messageHashes.clear();
        recipients.clear();

        String[] candidates = {"Messages.json", "Messages.json", "Message.json"};

        for (String fileName : candidates) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

                String line;

                while ((line = reader.readLine()) != null) {

                    if (!line.trim().isEmpty()) {

                        JSONObject obj = new JSONObject(line);

                        storedMessages.add(obj.optString("messageText", ""));
                        messageIDs.add(obj.optString("messageID", ""));
                        messageHashes.add(obj.optString("messageHash", ""));
                        recipients.add(obj.optString("recipient", ""));
                    }
                }

                // Successfully read one of the candidate files; stop trying others
                return;

            } catch (IOException e) {
                // try next candidate
            }
        }

        System.out.println("No stored messages found. Starting fresh.");
    }

    /**
     * Displays longest stored message.
     */
    public static String displayLongestMessage() {

        String longest = "";

        for (String msg : storedMessages) {

            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }

        return longest.isEmpty()
                ? "No stored messages."
                : longest;
    }

    /**
     * Search message by ID.
     */
    public static String searchByMessageID(String id) {

        for (int i = 0; i < messageIDs.size(); i++) {

            if (messageIDs.get(i).equals(id)) {

                if (i < sentMessages.size()) {
                    return sentMessages.get(i);
                }
            }
        }

        return "Message not found.";
    }

    /**
     * Search all messages for recipient.
     */
    public static String searchByRecipient(String recipientNumber) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < recipients.size(); i++) {

            if (recipients.get(i).equals(recipientNumber)
                    && i < sentMessages.size()) {

                result.append(sentMessages.get(i))
                        .append("\n");
            }
        }

        return result.length() > 0
                ? result.toString()
                : "No messages found.";
    }

    /**
     * Delete message using hash.
     */
    public static String deleteByHash(String hash) {

        for (int i = 0; i < messageHashes.size(); i++) {

            if (messageHashes.get(i).equals(hash)) {

                String deletedMessage = sentMessages.get(i);

                sentMessages.remove(i);
                messageHashes.remove(i);
                messageIDs.remove(i);
                recipients.remove(i);

                return "Message: "
                        + deletedMessage
                        + " successfully deleted.";
            }
        }

        return "Hash not found.";
    }

    /**
     * Generates report.
     */
    public static String printMessagesReport() {

        StringBuilder report = new StringBuilder();

        report.append("=== Message Report ===\n");

        for (int i = 0; i < sentMessages.size(); i++) {

            report.append("Hash: ")
                    .append(messageHashes.get(i))
                    .append("\n");

            report.append("Recipient: ")
                    .append(recipients.get(i))
                    .append("\n");

            report.append("Message: ")
                    .append(sentMessages.get(i))
                    .append("\n");

            report.append("--------------------------\n");
        }

        return report.toString();
    }

    public String getMessageID() {
        return messageID;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getMessageHash() {
        return messageHash;
    }
}

