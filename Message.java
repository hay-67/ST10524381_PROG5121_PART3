/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;
/**
 *
 * @author Student
 */
public class Message {
   private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String sendStatus;
    
    // Arrays used for Part 3

    private static java.util.ArrayList<String> sentMessages = new java.util.ArrayList<>();
    private static java.util.ArrayList<String> storedMessages = new java.util.ArrayList<>();
    private static java.util.ArrayList<String> disregardedMessages = new java.util.ArrayList<>();
    private static java.util.ArrayList<String> messageHashes = new java.util.ArrayList<>();
    private static java.util.ArrayList<String> messageIDs = new java.util.ArrayList<>();
    private static java.util.ArrayList<String> recipients = new java.util.ArrayList<>();


    // Constructor method
    public Message(int messageNumber, String recipient, String messageText) {

        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;

        generateMessageID();

        this.messageHash = createMessageHash();
    }

    // Generates random 10-digit message ID
    private void generateMessageID() {

        Random random = new Random();

        String id = "";

        for (int i = 0; i < 10; i++) {

            id += random.nextInt(10);
        }

        this.messageID = id;
    }

    // Checks validity of message ID length
    public boolean checkMessageID() {

        return messageID.length() == 10;
    }

    // Checks if recipient cell number is correct
    public String checkRecipientCell() {

        // Regex checks that number starts with +27 and contains 9 digits
        if (recipient.matches("^\\+27\\d{9}$")) {

            return "Cell phone number successfully captured.";

        } else {

            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Checks message length
    public String checkMessageLength() {

        if (messageText.length() <= 250) {

            return "Message ready to send.";

        } else {

            int over = messageText.length() - 250;

            return "Message exceeds 250 characters by " + over + "; please reduce the size.";
        }
    }

    // Creates message hash using ID, message number, message text
    public String createMessageHash() {

        String idPart = messageID.substring(0, 2);

        String[] words = messageText.split(" ");

        String firstWord = words[0];

        String lastWord = words[words.length - 1];

        String hash = idPart + ":" + messageNumber + ":" + firstWord + lastWord;

        return hash.toUpperCase();
    }

    // Gives user options to send, store, or delete
public String sentMessage() {

    Scanner scanner = new Scanner(System.in);

    System.out.println("What would you like to do with this message?");
    System.out.println("1) Send Message");
    System.out.println("2) Disregard Message");
    System.out.println("3) Store Message to send later");

    int option = scanner.nextInt();

    switch (option) {

        case 1:

            sendStatus = "Sent";

            // Saves sent message information
            sentMessages.add(messageText);
            messageHashes.add(messageHash);
            messageIDs.add(messageID);
            recipients.add(recipient);

            return "Message successfully sent.";

        case 2:

            sendStatus = "Disregarded";

            // Saves disregarded message
            disregardedMessages.add(messageText);

            return "Press 0 to delete the message.";

        case 3:

            sendStatus = "Stored";

            // Saves stored message information
            storedMessages.add(messageText);
            messageHashes.add(messageHash);
            messageIDs.add(messageID);
            recipients.add(recipient);

            storeMessage();

            return "Message successfully stored.";

        default:

            return "Invalid option.";
    }
}


    // Stores message details in JSON file
    public void storeMessage() {

        JSONObject obj = new JSONObject();

        obj.put("messageID", messageID);
        obj.put("recipient", recipient);
        obj.put("message", messageText);
        obj.put("messageHash", messageHash);

        try (FileWriter file = new FileWriter("messages.json", true)) {

            file.write(obj.toString());

            file.write(System.lineSeparator());

        } catch (IOException e) {

            System.out.println("Error writing to JSON file.");
        }
    }

    // Displays all stored messages and reipients
    public void displayStoredMessages() {

        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages found.");
             } else {
                System.out.println("=== STORED MESSAGES ===\n");
                for (int i = 0; i < storedMessages.size(); i++) {
                    System.out.println("Recipient: " + recipients.get(i));
                    System.out.println("Messages: " + storedMessages.get(i));
                    System.out.println();
                }
            }
      }
    
    // Finds and displays the longest stored message
    public String displayLongestMessage() {
        if (storedMessages.isEmpty()) {
            return "No stored messages found.";
        }
    String longestMessage = storedMessages.get(0);
    for (String message : storedMessages) {
        if (message.length() > longestMessage.length()) {
            longestMessage = message;       
        }
    }
    return longestMessage;
}
    
    // Displays a report of all stored messages
    public String printMessages() {
        
        // Checks if there are any stored messages
         if (storedMessages.isEmpty()) {
            return "No stored messages available.";
        }
        String report = "=== MESSAGE REPORT ===\n";

        // Loops through all stored messages
        for (int i = 0; i < storedMessages.size(); i++) {
            report +="Message ID: " + messageIDs.get(i)
                + "\nMessage Hash: " + messageHashes.get(i)
                + "\nRecipient: " + recipients.get(i)
                + "\nMessage: " + storedMessages.get(i)
                + "\n\n";
        }
        return report;
    }    
    
    // Searches for a message using its message ID
    public String searchByMessageID(String id) {
        for (int i = 0; i < messageIDs.size(); i++) {
            if (messageIDs.get(i).equals(id)) {
                return "Message Found: "
                    + "\nMessage ID: " + messageIDs.get(i)
                    + "\nRecipient: " + recipients.get(i)
                    + "\nMessage: " + storedMessages.get(i);
            }
        }
    return "Message ID not found.";
}
     // Searches for messages sent to a specific recipient
    public String searchByRecipient(String recipientNumber) {
        String results = "";
        for (int i = 0; i < recipients.size(); i++) {
            if (recipients.get(i).equals(recipientNumber)) {
                results += "Recipient: " + recipients.get(i)
                    + "\nMessage: " + storedMessages.get(i)
                    + "\n\n";
            }
        }
        if (results.isEmpty()) {
            return "No messages found for this recipient.";
        }
        return results;
    }   
    
    // Deletes a message using its hash
    public String deleteByHash(String hash) {
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equals(hash)) {
                messageHashes.remove(i);
                if (i < storedMessages.size()) {
                    storedMessages.remove(i);
                }
                if (i < messageIDs.size()) {
                    messageIDs.remove(i);
                }
                if (i < recipients.size()) {
                    recipients.remove(i);
                }
                return "Message successfully deleted.";
            }
        }
        return "Message hash not found.";
    }
    

    // Returns all sent messages
    public static java.util.ArrayList<String> getSentMessages() {
        return sentMessages;
    }
    // Returns all stored messages
    public static java.util.ArrayList<String> getStoredMessages() {
        return storedMessages;
    }
    // Returns all recipients
    public static java.util.ArrayList<String> getRecipients() {
        return recipients;
    }
    // Returns the generated message ID
    public String getMessageID() {
        return messageID;
    }
    // Returns the generated message hash
    public String getMessageHash() {
        return messageHash;
    } 
    
    // Adds a messages directly to the sent messages array
    public static void addSentMessage(String message) {
        sentMessages.add(message);
    }
    
    // Adds a message directly to the stored messages array
    public static void addStoredMessage(String message) {
        storedMessages.add(message);
    }
    
    // Adds a recipient directly to the recipient array
    public static void addRecipient(String recipient) {
        recipients.add(recipient);
    }
    
    // Adds a message ID directly to the message ID array
    public static void addMessageID(String id) {
        messageIDs.add(id); 
    }
    
    // Adds a message hash directly to the message hash array
    public static void addMessageHash(String hash) {
        messageHashes.add(hash);
    }
    
    public void loadStoredMessagesFromJSON() {

    try (Scanner fileReader = new Scanner(new java.io.File("messages.json"))) {

        while (fileReader.hasNextLine()) {

            String line = fileReader.nextLine();

            JSONObject obj = new JSONObject(line);

            storedMessages.add(obj.getString("message"));
            recipients.add(obj.getString("recipient"));
            messageIDs.add(obj.getString("messageID"));
            messageHashes.add(obj.getString("messageHash"));
        }

    } catch (Exception e) {

        System.out.println("Error reading JSON file.");
    }
}
    
    // Clears arrays before testing
    public static void clearArrays() {
        sentMessages.clear();
        storedMessages.clear();
        disregardedMessages.clear();
        messageHashes.clear();
        messageIDs.clear();
        recipients.clear(); 
    }
   
}