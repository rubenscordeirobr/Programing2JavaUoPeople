package uopeople.assignment.unit7.utils;

import java.time.LocalTime;

/**
 * Utility class containing helper methods for general-purpose operations.
 */
public class Util {

    /**
     * Attempts to parse an integer from a given string.
     *
     * @param string The input string to parse.
     * @return The parsed integer, or 0 if the input string is not a valid integer.
     */
    public static int tryParseInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Formats a message with the current time.
     *
     * @param message The message to format.
     * @return The formatted message.
     */
    public static String getCurrentTime() {
        LocalTime date = LocalTime.now();
        return String.format("%02d:%02d:%02d",
                date.getHour(),
                date.getMinute(),
                date.getSecond());
    }
}
