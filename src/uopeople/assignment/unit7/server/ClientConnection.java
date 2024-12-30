/*
 * Project: UoPeople Chat Application (Unit 7 Assignment)
 * Description: This is a chat server-client application designed to demonstrate networking, threading, and Java concepts.
 * Author: Rubens Cordeiro
 */

package uopeople.assignment.unit7.server;

import java.io.*;
import java.net.*;
import java.util.Map;

import uopeople.assignment.unit7.shared.ChatCommands;
import uopeople.assignment.unit7.shared.Logger;
import uopeople.assignment.unit7.shared.PrivateMessage;
import uopeople.assignment.unit7.utils.*;

/**
 * The ClientConnection class represents a single client connected to the chat
 * server.
 * It handles client-specific messaging, nickname changes, and disconnections.
 */
public class ClientConnection implements Runnable {

    /** Reference to the server managing this connection. */
    private final ChatServer server;

    /** The socket used for communication with the client. */
    private final Socket socket;

    /** Unique ID assigned to the client. */
    private final int clientId;

    /** Logger for logging client-specific events. */
    private final Logger logger;

    /** Output stream for sending messages to the client. */
    private PrintWriter out;

    /** Nickname of the client, if set. */
    private String nickname = null;

    /**
     * Constructs a new ClientConnection.
     *
     * @param server   the ChatServer managing this connection
     * @param socket   the socket used for client communication
     * @param clientId the unique ID assigned to the client
     */
    public ClientConnection(ChatServer server, Socket socket, int clientId) {
        this.socket = socket;
        this.clientId = clientId;
        this.server = server;
        this.logger = server.getLogger();
    }

    /**
     * Starts the client connection in a new thread.
     */
    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * The main run loop for handling client communication.
     */
    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            this.out = new PrintWriter(socket.getOutputStream(), true);

            this.send("Welcome, User " + this.clientId + "!");
            this.sendAll("User " + this.clientId + " has joined the chat.");

            String message;
            while ((message = in.readLine()) != null) {

                this.logger.Info("User " + this.getDisplayName() + ": " + message);
                if (message.equals("exit")) {
                    break;
                }

                if (message.startsWith(ChatCommands.COMMAND_NICKNAME)) {

                    this.nickname = message.substring(ChatCommands.COMMAND_NICKNAME.length()).trim();
                    this.logger.Success("User " + this.clientId + " changed nickname to " + this.nickname);
                    this.send("Your nickname has been changed to " + this.nickname);
                    this.sendAll("User " + this.clientId + " changed nickname to " + this.nickname);
                    continue;
                }

                if (message.startsWith(ChatCommands.COMMAND_ALL_USERS)) {
                    this.sendMessageWithAllUsers();
                    continue;
                }

                if (message.startsWith(ChatCommands.COMMAND_PRIVATE_MESSAGE)) {
                    PrivateMessage privateMessage = ChatUtil.getPrivateMessage(message);
                    if (privateMessage == null) {
                        this.logger.Error("Invalid private message format.");
                        continue;
                    }
                    this.sendPrivateMessage(privateMessage);
                    continue;
                }
                this.sendAll(this.getDisplayName() + ": " + message);

            }
        } catch (IOException e) {
            this.logger.Error("Error handling client " + clientId + ": " + e.getMessage());
        } finally {
            this.server.removeClient(clientId);
            this.closeConnection();
        }
    }

    /**
     * Sends a private message to the specified client.
     *
     * @param privateMessageTarget the private message to send
     */
    public void sendPrivateMessage(PrivateMessage privateMessageTarget) {

        if (privateMessageTarget.getClientId() == this.clientId) {
            this.logger.Error("You cannot send a private message to yourself.");
            return;
        }

        ClientConnection targetClient = server.getClient(privateMessageTarget.getClientId());
        if (targetClient == null) {
            this.logger.Error("Client " + privateMessageTarget.getClientId() + " not connected.");
            return;
        }

        PrivateMessage privateMessageOrigem = new PrivateMessage(
                this.clientId, privateMessageTarget.getMessage(),
                this.nickname);

        targetClient.send(privateMessageOrigem.getFullMessage());
    }

    /**
     * Sends a message to the client.
     *
     * @param message the message to send
     */
    public void send(String message) {
        try {
            out.println(message);
        } catch (Exception e) {
            this.logger.Error("Error sending message to client " + clientId + ": " + e.getMessage());
        }
    }

    /**
     * Broadcasts a message to all clients except the sender.
     *
     * @param message the message to broadcast
     */
    private void sendAll(String message) {
        this.server.broadcastMessage(message, this.clientId);
    }

    /**
     * Retrieves the display name of the client, using the nickname if available.
     *
     * @return the display name of the client
     */
    private String getDisplayName() {
        return (this.nickname != null && !this.nickname.isBlank())
                ? this.nickname
                : "User_" + clientId;
    }

    /**
     * Sends a list of all connected users to the client.
     */
    public void sendMessageWithAllUsers() {

        StringBuilder sb = new StringBuilder();
        sb.append(ChatCommands.COMMAND_ALL_USERS);

        for (Map.Entry<Integer, ClientConnection> entry : server.getClients().entrySet()) {

            ClientConnection client = entry.getValue();
            sb.append(client.clientId + ": " + client.getDisplayName() + "|");
        }
        this.send(sb.toString());
    }

    /**
     * Closes the connection to the client and releases resources.
     */
    private void closeConnection() {
        try {
            out.close();
            socket.close();
            out = null;
        } catch (IOException e) {
            logger.Error("Error closing connection for client " + clientId + ": " + e.getMessage());
        }
    }
}
