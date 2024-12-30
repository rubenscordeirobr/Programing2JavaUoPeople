/*
 * Project: UoPeople Chat Application (Unit 7 Assignment)
 * Description: This is a chat server-client application designed to demonstrate networking, threading, and Java concepts.
 * Author: Rubens Cordeiro
 */

package uopeople.assignment.unit7.shared;

/**
 * Represents a private message exchanged between clients.
 */
public class PrivateMessage {

    /** The client ID of the recipient. */
    private final int clientId;

    /** The content of the private message. */
    private final String message;

    /** The nickname of the sender. */
    private final String nickname;

    /**
     * Constructs a PrivateMessage with the specified recipient, content, and sender
     * nickname.
     *
     * @param clientId The ID of the recipient client.
     * @param message  The content of the message.
     * @param nickname The nickname of the sender.
     */
    public PrivateMessage(int clientId, String message, String nickname) {
        this.clientId = clientId;
        this.message = message;
        this.nickname = nickname;
    }

    /**
     * Retrieves the recipient's client ID.
     *
     * @return The recipient's client ID.
     */
    public int getClientId() {
        return this.clientId;
    }

    /**
     * Retrieves the content of the message.
     *
     * @return The message content.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Retrieves the sender's nickname.
     *
     * @return The sender's nickname.
     */
    public String getNickname() {
        return this.nickname;
    }

    /**
     * Retrieves the display name for the sender.
     * If no nickname is provided, defaults to "User_{clientId}".
     *
     * @return The display name for the sender.
     */
    public String getDisplayName() {
        return (this.nickname != null && !this.nickname.isBlank())
                ? this.nickname
                : "User_" + clientId;
    }

    /**
     * Retrieves the full message formatted for transmission.
     *
     * @return The formatted message.
     */
    public String getFullMessage() {
        return ChatCommands.COMMAND_PRIVATE_MESSAGE + this.message +
                ChatCommands.COMMAND_CLIENT_ID + this.clientId +
                ChatCommands.COMMAND_NICKNAME + this.nickname;
    }
}