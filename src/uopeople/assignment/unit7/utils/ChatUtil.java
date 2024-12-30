package uopeople.assignment.unit7.utils;

import uopeople.assignment.unit7.shared.ChatCommands;
import uopeople.assignment.unit7.shared.PrivateMessage;

/**
 * Utility class providing methods for handling chat-related operations.
 */
public class ChatUtil {

    /**
     * Parses a private message string to extract the message, client ID, and
     * nickname.
     *
     * @param fullMessage The raw string containing the private message.
     * @return A PrivateMessage object containing the parsed information, or null if
     *         parsing fails.
     */
    public static PrivateMessage getPrivateMessage(String fullMessage) {

        String message = null;
        String clientIdString = null;
        String nickname = null;

        // Split the message by the private message command
        String[] parts = fullMessage.split(ChatCommands.COMMAND_PRIVATE_MESSAGE, 2);
        if (parts.length > 1) {

            String temp = parts[1];

            // Extract client ID
            String[] clientParts = temp.split(ChatCommands.COMMAND_CLIENT_ID, 2);
            if (clientParts.length > 1) {
                message = clientParts[0].trim();
                temp = clientParts[1];
            }

            // Extract nickname
            String[] nicknameParts = temp.split(ChatCommands.COMMAND_NICKNAME, 2);
            if (nicknameParts.length > 1) {
                clientIdString = nicknameParts[0].trim();
                nickname = nicknameParts[1].trim();
            }
        }

        try {
            int clientId = Integer.parseInt(clientIdString);
            return new PrivateMessage(clientId, message, nickname);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Formats a message with the current time.
     *
     * @param message The message to format.
     * @return The formatted message.
     */
    public static String formatCurrentTime(String message) {
        return PrintUtil.getCyanMessage("\r[" + Util.getCurrentTime() + "] ") + message;
    }

    /**
     * Formats a private message with the current time.
     *
     * @param nickname The nickname of the sender.
     * @param message  The message to format.
     * @return The formatted message.
     */
    public static String formatCurrentTime(String nickname, String message) {
        return PrintUtil.getCyanMessage("\r[" + Util.getCurrentTime() + "] ") +
                PrintUtil.getYellowMessage(nickname) + ":" +
                message;
    }
}
