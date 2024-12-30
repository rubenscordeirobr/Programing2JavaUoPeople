/*
 * Project: UoPeople Chat Application (Unit 7 Assignment)
 * Description: This is a chat server-client application designed to demonstrate networking, threading, and Java concepts.
 * Author: Rubens Cordeiro
 */

package uopeople.assignment.unit7.server;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.function.Consumer;

import uopeople.assignment.unit7.shared.ChatConfigurations;
import uopeople.assignment.unit7.shared.LogEvent;
import uopeople.assignment.unit7.shared.Logger;
 
 /**
  * The ChatServer class is responsible for managing client connections,
  * broadcasting messages, and handling server operations.
  */
 public class ChatServer {
 
     /** Counter to assign unique IDs to each client. */
     private int clientIdCounter = 0;
 
     /** A map to keep track of connected clients by their IDs. */
     private final Map<Integer, ClientConnection> clients = new HashMap<>();
 
     /** The port on which the server listens for client connections. */
     private final int Port;
 
     /** Logger to handle server logging operations. */
     private final Logger logger = new Logger();
 
     /**
      * Constructs a ChatServer with the specified port.
      *
      * @param port the port number for the server to listen on
      * @throws IllegalArgumentException if the port number is invalid
      */
     public ChatServer(int port) {
         if (port < 0 || port > 65535) {
             throw new IllegalArgumentException("Invalid port number");
         }
         this.Port = port;
     }
 
     /**
      * Starts the chat server and listens for incoming client connections.
      */
     public void startServer() {
         this.logger.Info("Chat server started...");
         try (ServerSocket serverSocket = new ServerSocket(Port)) {
             while (true) {
                 Socket clientSocket = serverSocket.accept();
                 this.clientIdCounter++;
                 int clientId = clientIdCounter;
                 this.logger.Success("Client " + clientId + " connected.");
 
                 if (clients.size() >= ChatConfigurations.MAX_CONNECTIONS) {
                     this.logger.Warning("Max clients reached. Connection refused for client " + clientId);
                     clientSocket.close();
                     continue;
                 }
 
                 ClientConnection connection = new ClientConnection(this, clientSocket, clientId);
                 this.clients.put(clientId, connection);
                 connection.start();
             }
         } catch (IOException e) {
             this.logger.Error("Server error: " + e.getMessage());
         }
     }
 
     /**
      * Removes a client from the server.
      *
      * @param clientId the ID of the client to remove
      */
     public void removeClient(int clientId) {
         clients.remove(clientId);
         this.logger.Warning("Client " + clientId + " disconnected.");
     }
 
     /**
      * Sets a custom log event consumer for the server.
      *
      * @param logEventConsumer the log event consumer
      */
     public void setLogEventConsumer(Consumer<LogEvent> logEventConsumer) {
         this.logger.setLogEventConsumer(logEventConsumer);
     }
 
     /**
      * Retrieves the server's logger.
      *
      * @return the logger instance
      */
     public Logger getLogger() {
         return this.logger;
     }
 
     /**
      * Retrieves a specific client connection by its ID.
      *
      * @param targetClientId the ID of the target client
      * @return the ClientConnection instance, or null if not found
      */
     public ClientConnection getClient(int targetClientId) {
         return this.clients.get(targetClientId);
     }
 
     /**
      * Retrieves all connected clients.
      *
      * @return a map of client IDs to their connections
      */
     public Map<Integer, ClientConnection> getClients() {
         return this.clients;
     }
 
     /**
      * Broadcasts a message to all clients except the sender.
      *
      * @param message the message to broadcast
      * @param senderId the ID of the client sending the message
      */
     public void broadcastMessage(String message, int senderId) {
         for (Map.Entry<Integer, ClientConnection> entry : clients.entrySet()) {
             if (entry.getKey() != senderId) {
                 entry.getValue().send(message);
             }
         }
     }
 }
 