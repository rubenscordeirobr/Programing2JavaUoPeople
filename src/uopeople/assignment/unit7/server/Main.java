/*
 * Project: UoPeople Chat Application (Unit 7 Assignment)
 * Description: This is a chat server-client application designed to demonstrate networking, threading, and Java concepts.
 * Author: Rubens Cordeiro
 */

package uopeople.assignment.unit7.server;

import uopeople.assignment.unit7.shared.ChatConfigurations;
import uopeople.assignment.unit7.shared.LogEvent;
import uopeople.assignment.unit7.utils.*;

/**
 * Main entry point for the Chat Server application.
 * This class initializes and starts the server, and handles log events.
 */
public class Main {

    /** Singleton instance of the ChatServer. */
    private static final ChatServer chatServer = new ChatServer(ChatConfigurations.PORT);

    /**
     * Main method to launch the Chat Server.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        chatServer.setLogEventConsumer(Main::log);
        chatServer.startServer();
    }

    /**
     * Handles logging of events with different log levels.
     *
     * @param logEvent The event to be logged.
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
}
