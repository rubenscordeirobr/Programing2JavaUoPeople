/*
 * Project: UoPeople Chat Application ( Unit 7 Assignment)
 * Description: This is a chat server-client application designed to demonstrate networking, threading, and Java concepts.
 * Author:  Rubens Cordeiro
 */


package uopeople.assignment.unit7.client;

import java.io.*;
import java.net.*;
import java.util.function.Consumer;

import uopeople.assignment.unit7.*;
import uopeople.assignment.unit7.shared.ChatCommands;
import uopeople.assignment.unit7.shared.ChatServerStatus;
import uopeople.assignment.unit7.shared.LogEvent;
import uopeople.assignment.unit7.shared.Logger;
/**
 * Represents a client for connecting to the chat server.
 * Handles sending and receiving messages.
 */
public class ChatClient {

    /**
     * The nickname of the client.
     */
    private final String nickname;

    /**
     * The server's address.
     */
    private final String serverAddress;

    /**
     * The server's port.
     */
    private final int serverPort;

    /**
     * Logger for logging messages.
     */
    private final Logger logger = new Logger();

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ChatServerStatus status = ChatServerStatus.DISCONNECTED;

    private Consumer<String> receiveMessageConsumer;

    /**
     * Initializes the ChatClient with server details and a nickname.
     *
     * @param serverAddress the server's address
     * @param serverPort the server's port
     * @param nickname the client's nickname
     */
    public ChatClient(String serverAddress, int serverPort, String nickname) {

        if (serverPort < 0 || serverPort > 65535) {
            throw new IllegalArgumentException("Invalid port number");
        }

        this.serverPort = serverPort;
        this.serverAddress = serverAddress;
        this.nickname = nickname;
    }

    /**
     * Starts the client and connects to the server.
     */
    public void start() {

        try {
            this.status = ChatServerStatus.CONNECTING;
            this.logger.Warning("Connecting to server " + this.serverAddress + ":" + this.serverPort);
            this.socket = new Socket(serverAddress, this.serverPort);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.logger.Success("Connected to server " + this.serverAddress + ":" + this.serverPort);
            this.status = ChatServerStatus.CONNECTED;

        } catch (IOException e) {
            this.logger.Error("Error connecting to server: " + e.getMessage());
            this.status = ChatServerStatus.DISCONNECTED;
            return;
        }

        new Thread(this::receiveMessages).start();
        this.sendMessage(ChatCommands.COMMAND_NICKNAME + this.nickname);
    }

    /**
     * Gets the current status of the client.
     *
     * @return the client's status
     */
    public ChatServerStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the log event consumer for logging events.
     *
     * @param logEventConsumer the consumer to handle log events
     */
    public void setLogEventConsumer(Consumer<LogEvent> logEventConsumer) {
        this.logger.setLogEventConsumer(logEventConsumer);
    }

    /**
     * Sets the consumer for handling received messages.
     *
     * @param receiveMessageConsumer the consumer to handle received messages
     */
    public void setReceiveMessageConsumer(Consumer<String> receiveMessageConsumer) {
        this.receiveMessageConsumer = receiveMessageConsumer;
    }

    /**
     * Sends a message to the server.
     *
     * @param message the message to send
     */
    public void sendMessage(String message) {
        try {
            this.out.println(message);
        } catch (Exception e) {
            this.logger.Error("Error sending message: " + e.getMessage());
            this.closeConnection();
        }
    }

    /**
     * Receives messages from the server in a separate thread.
     */
    private void receiveMessages() {

        try {

            String message;
            while ((message = this.in.readLine()) != null) {

                if (this.receiveMessageConsumer != null) {
                    this.receiveMessageConsumer.accept(message);
                }
            }

        } catch (IOException e) {
            this.logger.Error("Error receiving message: " + e.getMessage());
            this.closeConnection();
        }
    }

    /**
     * Closes the connection to the server and releases resources.
     */
    public void closeConnection() {
        try {
            if (this.in != null) {
                this.in.close();
            }

            if (this.out != null) {
                this.out.close();
            }

            if (this.socket != null) {
                this.socket.close();
                this.socket = null;
            }
        } catch (IOException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
        System.out.println("Disconnected from the server.");
        this.status = ChatServerStatus.DISCONNECTED;
    }
}
