/*
 * Project: UoPeople Chat Application (Unit 7 Assignment)
 * Description: This is a chat server-client application designed to demonstrate networking, threading, and Java concepts.
 * Author: Rubens Cordeiro
 */

 package uopeople.assignment.unit7.shared;

 /**
  * Defines a set of commands used for communication in the chat application.
  */
 public class ChatCommands {
 
     /** Command to change the user's nickname. */
     public static final String COMMAND_NICKNAME = "/nickname";
 
     /** Command to specify the client ID in messages. */
     public static final String COMMAND_CLIENT_ID = "/clientId";
 
     /** Command to exit the chat application. */
     public static final String COMMAND_EXIT = "/exit";
 
     /** Command to list all users connected to the chat server. */
     public static final String COMMAND_ALL_USERS = "/allusers";
 
     /** Command to send a private message to another user. */
     public static final String COMMAND_PRIVATE_MESSAGE = "/private";
 }
 