/*
 * Project: UoPeople Chat Application (Unit 7 Assignment)
 * Description: This is a chat server-client application designed to demonstrate networking, threading, and Java concepts.
 * Author: Rubens Cordeiro
 */

package uopeople.assignment.unit7.shared;

import java.util.function.Consumer;
/**
 * Handles logging operations for the chat application.
 * Supports different log levels and allows custom log event consumers.
 */
public class Logger {

    /** Consumer to handle log events. */
    private Consumer<LogEvent> logEventConsumer;

    /** Default constructor for the Logger class. */
    public Logger() {
    }

    /**
     * Sets a custom consumer for log events.
     *
     * @param logEventConsumer The log event consumer.
     */
    public void setLogEventConsumer(Consumer<LogEvent> logEventConsumer) {
        this.logEventConsumer = logEventConsumer;
    }

    /**
     * Logs an informational message.
     *
     * @param message The message to log.
     */
    public void Info(String message) {
        this.Log(message, TypeLog.INFO);
    }

    /**
     * Logs an error message.
     *
     * @param message The message to log.
     */
    public void Error(String message) {
        this.Log(message, TypeLog.ERROR);
    }

    /**
     * Logs a warning message.
     *
     * @param message The message to log.
     */
    public void Warning(String message) {
        this.Log(message, TypeLog.WARNING);
    }

    /**
     * Logs a success message.
     *
     * @param message The message to log.
     */
    public void Success(String message) {
        this.Log(message, TypeLog.SUCCESS);
    }

    /**
     * Logs a message with the specified log type.
     *
     * @param message The message to log.
     * @param typeLog The type of the log event.
     */
    private void Log(String message, TypeLog typeLog) {
        if (logEventConsumer != null) {
            logEventConsumer.accept(new LogEvent(message, typeLog));
        }
    }
}
