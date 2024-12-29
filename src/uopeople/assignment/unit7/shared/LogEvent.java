/*
 * Project: UoPeople Chat Application (Unit 7 Assignment)
 * Description: This is a chat server-client application designed to demonstrate networking, threading, and Java concepts.
 * Author: Rubens Cordeiro
 */

 package uopeople.assignment.unit7.shared;

 import java.time.LocalDateTime;
 
 /**
  * Represents a log event in the chat application.
  * Each log event contains a timestamp, a message, and a log type.
  */
 public class LogEvent {
 
     /** The timestamp of when the log event occurred. */
     private final LocalDateTime DateTime;
 
     /** The message associated with the log event. */
     private final String message;
 
     /** The type of the log event (INFO, SUCCESS, WARNING, ERROR). */
     private final TypeLog typeLog;
 
     /**
      * Constructs a LogEvent with a message and a log type.
      *
      * @param message The log message.
      * @param typeLog The type of the log event.
      */
     public LogEvent(String message, TypeLog typeLog) {
         this.DateTime = LocalDateTime.now();
         this.message = message;
         this.typeLog = typeLog;
     }
 
     /**
      * Retrieves the timestamp of the log event.
      *
      * @return The timestamp of the log event.
      */
     public LocalDateTime getDateTime() {
         return this.DateTime;
     }
 
     /**
      * Retrieves the message of the log event.
      *
      * @return The log message.
      */
     public String getMessage() {
         return this.message;
     }
 
     /**
      * Retrieves the full log message including type, timestamp, and message.
      *
      * @return The formatted log message.
      */
     public String getFullMessage() {
         return this.typeLog + " - " + this.DateTime + " - " + this.message;
     }
 
     /**
      * Retrieves the type of the log event.
      *
      * @return The type of the log event.
      */
     public TypeLog getTypeLog() {
         return this.typeLog;
     }
 }
 