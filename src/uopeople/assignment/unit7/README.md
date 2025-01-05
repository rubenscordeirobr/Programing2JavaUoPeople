# UoPeople Chat Application (Unit 7 Assignment)

## Overview

This is a simple client-server chat application implemented in Java. The application demonstrates networking, threading, and object-oriented programming concepts. Multiple clients can connect to a central server, send messages, and receive messages from other users. The application supports private messaging and nickname changes.

## Features

- **Server-Client Communication**: Handles multiple clients concurrently.
- **Broadcast Messaging**: Sends messages to all connected clients.
- **Private Messaging**: Allows sending direct messages to specific users.
- **Nicknames**: Clients can set and update their nicknames.
- **Logging**: Detailed logging with different severity levels (INFO, SUCCESS, WARNING, ERROR).

## Requirements

- **Java Version**: JDK 8 or later
- **IDE/Editor**: Any Java IDE or a simple text editor with terminal/command line.

## Running the Application

### Server Setup

1. Navigate to the server source code directory.
2. Compile the `Main` class in the `server` package:
   ```sh
   javac uopeople/assignment/unit7/server/Main.java
   ```
3. Run the server:
   ```sh
   java uopeople.assignment.unit7.server.Main
   ```
   Or Run using PowerShell script: 
   ```
   ./scripts/RunChatServer.ps1
   ```


### Client Setup

1. Navigate to the client source code directory.
2. Compile the `Main` class in the `client` package:
   ```sh
   javac uopeople/assignment/unit7/client/Main.java
   ```
3. Run the client:
   ```sh
   java uopeople.assignment.unit7.client.Main
   ```
   Or Run using PowerShell script: 
   ```
   ./scripts/RunChatClient.ps1
   ```
4. Enter your nickname and follow the on-screen instructions to chat.

## Application Commands

### Client Commands

- `/nickname [new_nickname]`: Change y*our nickname.*
- `/allusers`: Get a list of all connected users.
- `/private [message]`: Send a private message to a specific user (after specifying user ID).
- `/exit`: Disconnect from the chat server.

## Code Structure

### Packages

- **`uopeople.assignment.unit7.server`**: Contains server-side logic including `ChatServer`, `ClientConnection`, and `Main` classes.
- **`uopeople.assignment.unit7.client`**: Contains client-side logic including `ChatClient` and `Main` classes.
- **`uopeople.assignment.unit7.utils`**: Contains utility classes for input handling (`InputUtil`), message formatting (`PrintUtil`), and more.
- **`uopeople.assignment.unit7.shared`**: Contains shared resources such as `ChatCommands`, `ChatConfigurations`, `Logger`, and `PrivateMessage` classes.

### Key Classes

1. **ChatServer**: Manages client connections and message broadcasting.
2. **ClientConnection**: Represents a single connection to a client.
3. **ChatClient**: Handles client-side operations like connecting to the server and sending/receiving messages.
4. **LogEvent/Logger**: Implements logging with various severity levels.
5. **PrivateMessage**: Encapsulates private message data.
 
 
---

Thank you for exploring the UoPeople Chat Application!

