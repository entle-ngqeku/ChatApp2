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
# README – Message Class

## Overview

The `Message` class is responsible for handling all messaging functionality in the ChatApp2 application. It allows users to create messages, validate recipient phone numbers, validate message length, generate unique message IDs and hashes, send messages, store messages in a JSON file, search messages, delete messages, and generate message reports.

---

## Features

### Message Creation

Each message contains:

* Message ID
* Message Number
* Recipient Number
* Message Text
* Message Hash

The constructor automatically generates a Message ID and Message Hash when a new message object is created.

---

## Message ID Generation

The system automatically generates a random 10-digit Message ID.

Example:

1234567890

The `checkMessageID()` method verifies that the generated ID does not exceed 10 characters.

---

## Recipient Validation

The `checkRecipientCell()` method validates the recipient's cellphone number.

Requirements:

* Must start with `+27`
* Must not exceed 13 characters

Example:

+27831234567

If valid:

Cell phone number successfully captured.

If invalid:

Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.

---

## Message Length Validation

The `checkMessageLength()` method checks that the message does not exceed 250 characters.

If valid:

Message ready to send.

If invalid:

Message exceeds 250 characters by X; please reduce the size.

---

## Message Hash Generation

The `createMessageHash()` method generates a message hash using:

1. The message number formatted to two digits.
2. A fixed value of 0.
3. The first eight uppercase alphabetic characters from the message text.

Example:

01:0:HELLOWOR

---

## Message Actions

The `sentMessage()` method provides three options:

### 1. Send Message

Stores:

* Message Text
* Message Hash
* Message ID
* Recipient Number

Returns a confirmation containing all message details.

### 2. Disregard Message

Stores the message in the `disregardedMessages` list.

Returns:

Message disregarded.

### 3. Store Message

Stores message details inside a JSON file named:

Message.json

Stored information:

* Message Text
* Recipient Number
* Message Hash
* Message ID

Returns:

Message stored successfully.

---

## Data Structures

The class uses ArrayLists to store message information.

### Sent Messages

```java
sentMessages
```

Stores all sent message texts.

### Disregarded Messages

```java
disregardedMessages
```

Stores ignored messages.

### Stored Messages

```java
storedMessages
```

Stores messages loaded from JSON files.

### Message Hashes

```java
messageHashes
```

Stores all generated message hashes.

### Message IDs

```java
messageIDs
```

Stores all generated message IDs.

### Recipients

```java
recipients
```

Stores recipient phone numbers.

---

## Loading Stored Messages

Method:

```java
loadStoredMessages()
```

Reads stored messages from:

* Messages.json
* Message.json

The method loads:

* Message Text
* Message ID
* Message Hash
* Recipient Number

into their respective ArrayLists.

---

## Display Longest Message

Method:

```java
displayLongestMessage()
```

Finds and returns the longest stored message.

If no messages exist:

No stored messages.

---

## Search by Message ID

Method:

```java
searchByMessageID(String id)
```

Searches for a message using its Message ID.

Returns:

* The message text if found.
* "Message not found." if no match exists.

---

## Search by Recipient

Method:

```java
searchByRecipient(String recipientNumber)
```

Returns all messages associated with a specific recipient number.

If no messages are found:

No messages found.

---

## Delete Message by Hash

Method:

```java
deleteByHash(String hash)
```

Deletes a message using its Message Hash.

Returns:

Message: [message text] successfully deleted.

If not found:

Hash not found.

---

## Print Message Report

Method:

```java
printMessagesReport()
```

Generates a report displaying:

* Message Hash
* Recipient Number
* Message Text

Example:

=== Message Report ===

Hash: 01:0:HELLOWOR
Recipient: +27831234567
Message: Hello World
--------------------

---

## Getter Methods

The class provides getter methods for:

```java
getMessageID()
getMessageNumber()
getRecipient()
getMessageText()
getMessageHash()
```

These methods allow controlled access to private message attributes

## Class Name

Message.java

## Package

com.mycompany.chatapp2
