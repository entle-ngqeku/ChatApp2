/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp2;
import org.json.JSONObject;
/**
 *
 * @author Student
 */
public class Message {
    
    // Fields - the data a message holds
    private String messageID;        // 10-digit auto-generated
    private int messageNumber;       // from loop counter
    private String recipient;        // validated cell number
    private String messageText;      // max 250 chars
    private String messageHash;      // auto-generated
    private String sendStatus;       // Sent, Stored, or Disregarded
    
    // Constructor
    public Message(String recipient, String messageText, int messageNumber) {
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageNumber = messageNumber;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }
    
    /**
     * Generates a random 10-digit message ID using string manipulation
     * @return 10-character string ID
     */
    private String generateMessageID() {
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append((int)(Math.random() * 10)); // Random digit 0-9
        }
        return id.toString();
    }
    
    /**
     * Checks if message ID is not more than 10 characters
     * @return true if valid, false otherwise
     */
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }
    
    /**
     * Validates recipient cell number (reuses Part 1 logic!)
     * @return Success or failure message
     */
    public String checkRecipientCell() {
        // Reuse from Login.java - international code + max 10 chars after
        if (recipient.startsWith("+27") && recipient.length() <= 13) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
    
    /**
     * Checks message length (max 250 chars)
     * @return Success or failure message with exact char count
     */
    public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int over = messageText.length() - 250;
            return "Message exceeds 250 characters by " + over + "; please reduce the size.";
        }
    }
    
    /**
     * Creates message hash: first2digits:number:firstword+lastword (ALL UPPERCASE)
     * @return Formatted hash string
     */
    public String createMessageHash() {
        String idPart = messageID.substring(0, 2);
        String[] words = messageText.split(" ");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        String hash = idPart + ":" + messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }
    
    /**
     * Asks user what to do: Send, Disregard, or Store
     * @return Status message
     */
    public String sentMessage() {
        System.out.println("What would you like to do with this message?");
        System.out.println("1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message to send later");
        
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int option = scanner.nextInt();
        
        switch (option) {
            case 1:
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                storeMessage();
                return "Message successfully stored.";
            default:
                return "Invalid option selected.";
        }
    }
    
    /**
     * Saves message to JSON file using org.json library
     */
    public void storeMessage() {
        try {
            JSONObject obj = new JSONObject();
            obj.put("messageID", this.messageID);
            obj.put("recipient", this.recipient);
            obj.put("message", this.messageText);
            
            java.io.FileWriter fw = new java.io.FileWriter("messages.json", true);
            fw.write(obj.toString() + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("Error saving message: " + e.getMessage());
        }
    }
    
    /**
     * Returns all message details in correct order
     * @return Formatted string with ID, Hash, Recipient, Message
     */
    public String printMessages() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + messageText;
    }
    
    /**
     * Returns total count of messages sent
     * @return Message count
     */
    public int returnTotalMessages() {
        return 1; // Simplified for this demo
    }
    
    // Getters for testing
    public String getMessageID() { return messageID; }
    public int getMessageNumber() { return messageNumber; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public String getMessageHash() { return messageHash; }
}
