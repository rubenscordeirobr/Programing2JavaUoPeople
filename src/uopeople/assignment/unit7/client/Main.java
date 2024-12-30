/*
 * Project: UoPeople Chat Application (Unit 7 Assignment)
 * Description: This is a chat server-client application designed to demonstrate networking, threading, and Java concepts.
 * Author:  Rubens Cordeiro
 */

package uopeople.assignment.unit7.client;

import java.util.HashMap;
import java.util.Map;

import uopeople.assignment.unit7.shared.ChatCommands;
import uopeople.assignment.unit7.shared.ChatConfigurations;
import uopeople.assignment.unit7.shared.ChatServerStatus;
import uopeople.assignment.unit7.shared.LogEvent;
import uopeople.assignment.unit7.shared.PrivateMessage;
import uopeople.assignment.unit7.utils.*;

/**
 * Entry point for the chat client application.
 * This class handles user input, connection to the chat server, and
 * communication management.
 */
public class Main {

    private static String nickname;
    private static ChatClient chatClient;
    private static final Map<Integer, String> usersConnected = new HashMap<>();

    /**
     * Main method to start the client application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {

        System.out.println("Hello, welcome to the UoPeople chat Unit 7!");
        nickname = InputUtil.getInputFromUser("Please enter your nickname for the Chat: ");
        while (true) {
            connectChatServer();
        }
    }

    /**
     * Connects to the chat server and initializes the client.
     */
    private static void connectChatServer() {

        chatClient = new ChatClient(ChatConfigurations.SERVER_HOST, ChatConfigurations.PORT, nickname);
        chatClient.setLogEventConsumer(Main::log);
        chatClient.setReceiveMessageConsumer(Main::receiveMessage);

        new Thread(Main::updateAllUsers).start();

        while (true) {
            chatClient.start();
            if (chatClient.getStatus() == ChatServerStatus.CONNECTED) {
                startChat();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Starts the chat session, allowing user interaction.
     */
    private static void startChat() {

        PrintUtil.printSuccessMessage("Connection to server successful.");
        System.out.println("Type '" + ChatCommands.COMMAND_EXIT + "' to close the chat client.");
        System.out.println("Type '" + ChatCommands.COMMAND_ALL_USERS + "' to get all users connected to the chat server.");
        System.out.println("Type '" + ChatCommands.COMMAND_PRIVATE_MESSAGE + "' to send a private message to a user.");
        System.out.println("Type '" + ChatCommands.COMMAND_NICKNAME + "' to change your nickname.");

        while (chatClient.getStatus() == ChatServerStatus.CONNECTED) {

            String message = InputUtil.getInputFromUser("\r" + nickname + ": ");

            PrintUtil.printMessage(ChatUtil.formatCurrentTime(nickname, message));

            if (message.startsWith(ChatCommands.COMMAND_EXIT)) {
                exitChat();
                break;
            }

            if (message.startsWith(ChatCommands.COMMAND_NICKNAME)) {
                sendChangeNickname(message);
                continue;
            }

            if (message.startsWith(ChatCommands.COMMAND_ALL_USERS)) {
                chatClient.sendMessage(ChatCommands.COMMAND_ALL_USERS);
                displayAllUsers();
                continue;
            }

            if (message.startsWith(ChatCommands.COMMAND_PRIVATE_MESSAGE)) {
                sendPrivateMessage(message);
                continue;
            }

            chatClient.sendMessage(message);
        }
    }

    /**
     * Exits the chat application and disconnects from the server.
     */
    private static void exitChat() {

        chatClient.sendMessage(ChatCommands.COMMAND_EXIT);
        chatClient.closeConnection();
        PrintUtil.printMessage("Thank you for using the chat client. Goodbye!");
        System.exit(0);
    }

    /**
     * Changes the user's nickname.
     *
     * @param message The command to change nickname.
     */
    private static void sendChangeNickname(String message) {

        message = message.substring(ChatCommands.COMMAND_NICKNAME.length());

        if (message.isBlank()) {
            message = InputUtil.getInputFromUser("Enter the new nickname: ");

            if (message.isBlank()) {
                PrintUtil.printFailMessage("Invalid nickname.");
                return;
            }
        }

        chatClient.sendMessage(ChatCommands.COMMAND_NICKNAME + message);
    }

    /**
     * Sends a private message to a specific user.
     *
     * @param message The command containing the private message.
     */
    private static void sendPrivateMessage(String message) {

        if (usersConnected.size() == 0) {
            PrintUtil.printWarningMessage("No users connected to the chat server.");
            return;
        }

        message = message.substring(ChatCommands.COMMAND_PRIVATE_MESSAGE.length());

        if (message.isBlank()) {
            message = InputUtil.getInputFromUser("Enter the message to send to the user: ");

            if (message.isBlank()) {
                PrintUtil.printFailMessage("Invalid message.");
                return;
            }
        }

        displayAllUsers();

        int clientId = InputUtil.getIntInputFromUser("Enter the User ID: ");
        if (!usersConnected.containsKey(clientId)) {
            PrintUtil.printFailMessage("Invalid User ID.");
            return;
        }

        String nickname = usersConnected.get(clientId);
        PrivateMessage privateMessage = new PrivateMessage(clientId, message, nickname);

        chatClient.sendMessage(privateMessage.getFullMessage());
    }

    /**
     * Displays all users currently connected to the chat server.
     */
    private static void displayAllUsers() {

        PrintUtil.printMessage("Users connected to the chat server:");
        String header = String.format("%-10s %-30s", "User ID", "Nickname");
        PrintUtil.printMessage(header);

        for (Map.Entry<Integer, String> user : usersConnected.entrySet()) {
            String row = String.format("%-10s %-30s", user.getKey(), user.getValue());
            PrintUtil.printMessage(row);
        }
    }

    /**
     * Logs events with different severity levels.
     *
     * @param logEvent The event to log.
     */
    public static void log(LogEvent logEvent) {

        String message = ChatUtil.formatCurrentTime(logEvent.getFullMessage());
         switch (logEvent.getTypeLog()) {
 
             case INFO:
                 PrintUtil.printMessage(message);
                 break;
 
             case SUCCESS:
                 PrintUtil.printSuccessMessage(message);
                 break;
 
             case WARNING:
                 PrintUtil.printWarningMessage(message);
                 break;
 
             case ERROR:
                 PrintUtil.printFailMessage(message);
                 break;
 
             default:
                 throw new IllegalArgumentException("Event type not supported: " + logEvent.getTypeLog());
         }
    }

    /**
     * Handles messages received from the server.
     *
     * @param message The received message.
     */
    public static void receiveMessage(String message) {

        if (message.startsWith(ChatCommands.COMMAND_ALL_USERS)) {
            fillAllUsers(message);
            return;
        }

        if (message.startsWith(ChatCommands.COMMAND_PRIVATE_MESSAGE)) {
            displayPrivateMessage(message);
            return;
        }

        PrintUtil.printMessage(ChatUtil.formatCurrentTime(message));
        PrintUtil.printInputUserMessage("\r" + nickname + ": ");
    }

    /**
     * Displays a private message received from another user.
     *
     * @param message The received private message.
     */
    private static void displayPrivateMessage(String message) {
       
        PrivateMessage privateMessage = ChatUtil.getPrivateMessage(message);
        if (privateMessage == null) {
            PrintUtil.printFailMessage("Invalid private message. " + message);
            return;
        }

        PrintUtil.printWarningMessage(ChatUtil.formatCurrentTime("Private message from " + privateMessage.getDisplayName() + ": "),
                privateMessage.getMessage());
    }

    /**
     * Updates the list of all connected users.
     *
     * @param message The message containing user details.
     */
    private static void fillAllUsers(String message) {
       
        message = message.substring(ChatCommands.COMMAND_ALL_USERS.length());
        String[] users = message.split("\\|");

        usersConnected.clear();

        for (String user : users) {
            String[] userParts = user.split(":");
            if (userParts.length != 2) {
                continue;
            }

            int userId = Util.tryParseInt(userParts[0]);
            if (userId == 0) {
                continue;
            }

            String nickname = userParts[1];
            usersConnected.put(userId, nickname);
        }
    }

    /**
     * Periodically requests the list of all connected users.
     */
    private static void updateAllUsers() {
       
        while (true) {
            if (chatClient.getStatus() == ChatServerStatus.CONNECTED) {
                chatClient.sendMessage(ChatCommands.COMMAND_ALL_USERS );
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
