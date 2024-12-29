/*
 * Project: UoPeople Chat Application (Unit 7 Assignment)
 * Description: This is a chat server-client application designed to demonstrate networking, threading, and Java concepts.
 * Author: Rubens Cordeiro
 */

 package uopeople.assignment.unit7.shared;

 /**
  * Enumeration representing the possible statuses of the chat server.
  */
 public enum ChatServerStatus {
 
     /** The server is in the process of establishing connections. */
     CONNECTING,
 
     /** The server is actively connected and handling clients. */
     CONNECTED,
 
     /** The server is disconnected and not operational. */
     DISCONNECTED
 }
 