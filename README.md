# PROG5121 Programming 1A - ChatApp Part 3

## Student Information

* Name: Hailey Bruintjies
* Student Number: ST10524381
* Module: Programming 1A

***

## Project Overview

This project is Part 3 of the ChatApp assignment (continuation of Part 1 and Part 2).

The application allows users to:

* Register an account
* Login with their details
* Send messages
* Validate recipient phone numbers
* Validate message length
* Generate message hashes
* Store messages in JSON format
* Display stored messages
* Search for messages
* Delete messages using a message hash
* Generate message reports
* Run automated unit tests using JUnit

***

## Features

### User Registration

The user can register for an account using a:

* Username
* Password
* Phone Number

### User Login

Registered users can login using their username and password

### Messaging

The messaging system allows users to:

* Send messages
* Store messages
* Disregard messages
* Generate message IDs
* Generate message hashes

### Stored Messages Menu

Users can:

* Display all stored messages
* Display the longest stored message
* Search for a message using its message ID
* Search for messages using a recipient number
* Delete a message using a message hash
* Display a full message report

### JSON Storage

Stored messages are written to a JSON file called:

* messages.json

### Unit Testing

JUnit tests were created for:

* Message length validation
* Recipient number validation
* Message ID validation
* Message hash generation
* Send message options
* Sent message array population
* Longest message display
* Search by message ID
* Search by recipient
* Delete by message hash
* Message report generation

## Classes Used

### MainApp Class

Handles:

* User Registration
* User Login
* Menu Options
* Message Creation
* Stored Message Menu

***

### Login Class

Handles:

* Username Validation
* Password Validation
* Cellphone Number Validation
* User Registration
* User Login Verification

***

### Message Class

Handles:

* Message ID Generation
* Message Hash Generation
* Message Validation
* Sending Messages
* Storing Messages
* Disregarding Messages
* JSON File Storage
* Searching Messages
* Deleting Messages
* Generating Reports

***

### LoginTest Class

Contains JUnit tests for:

* Username Validation
* Password Complexity
* Cellphone Number Validation
* Login Functionality

***

### MessageTest Class

Contains JUnit tests for:

* Message Length Validation
* Recipient Validation
* Message Hash Generation
* Message ID Validation
* Send Message Options
* Sent Message Array Population
* Longest Stored Message

## YouTube Link
https://youtu.be/zjOEqIIVYO8
* Search by Message ID
* Search by Recipient
* Delete by Hash
* Message Report Generation
