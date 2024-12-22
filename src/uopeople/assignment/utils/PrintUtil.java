package uopeople.assignment.utils;

/**
 * The PrintUtil class provides utility methods for printing messages to the console
 * with colored text to enhance user interaction. It uses ANSI escape codes to
 * format the console output with specific colors, such as green for success,
 * red for failure, yellow for warnings, and blue for additional information.
 */
public class PrintUtil {

    // ANSI escape code to reset text color to default
    public static final String ANSI_RESET = "\u001B[0m";
    // ANSI escape code for red text color (used for failure messages)
    public static final String ANSI_RED = "\u001B[31m";
    // ANSI escape code for green text color (used for success messages)
    public static final String ANSI_GREEN = "\u001B[32m";
    // ANSI escape code for yellow text color (used for warning or secondary messages)
    public static final String ANSI_YELLOW = "\u001B[33m";
    // ANSI escape code for blue text color (used for additional or informational messages)
    public static final String ANSI_BLUE = "\u001B[34m";

    /**
     * Prints a failure message to the console in red text.
     *
     * @param message The failure message to be printed.
     */
    public static void printFailMessage(String message) {
        System.out.println(ANSI_RED + message + ANSI_RESET);
    }

    /**
     * Prints a success message to the console in green text.
     *
     * @param message The success message to be printed.
     */
    public static void printSuccessMessage(String message) {
        System.out.println(ANSI_GREEN + message + ANSI_RESET);
    }

    
    /**
     * Prints a user input message to the console in yellow text.
     *
     * @param message the user input message to be printed
     */
    public static void printInputUserMessage(String message) {
        System.out.println(ANSI_YELLOW + message + ANSI_RESET);
    }

    /**
     * Prints a message with green, yellow, and blue colors in a single output.
     *
     * @param greenMessage  The message to be printed in green.
     * @param yellowMessage The message to be printed in yellow.
     * @param blueMessage   The message to be printed in blue.
     */
    public static void printColoredMessage(String greenMessage, String yellowMessage, String blueMessage) {
        String coloredMessage = ANSI_GREEN + greenMessage + " " + ANSI_YELLOW + yellowMessage + " " + ANSI_BLUE + blueMessage + ANSI_RESET;
        System.out.println(coloredMessage);
    }

    /**
     * Returns a formatted string with green, yellow, and blue colors combined.
     *
     * @param greenMessage  The message to be formatted in green.
     * @param yellowMessage The message to be formatted in yellow.
     * @param blueMessage   The message to be formatted in blue.
     * @return The formatted string with colored messages.
     */
    public static String getColoredMessage(String greenMessage, String yellowMessage, String blueMessage) {
        return ANSI_GREEN + greenMessage + " " + ANSI_YELLOW + yellowMessage + " " + ANSI_BLUE + blueMessage + ANSI_RESET;
    }

    /**
     * Returns a message formatted in green text.
     *
     * @param greenMessage The message to be formatted in green.
     * @return The formatted green message as a string.
     */
    public static String getGreenMessage(String greenMessage) {
        return ANSI_GREEN + greenMessage + ANSI_RESET;
    }

    /**
     * Returns a message formatted in yellow text.
     *
     * @param yellowMessage The message to be formatted in yellow.
     * @return The formatted yellow message as a string.
     */
    public static String getYellowMessage(String yellowMessage) {
        return ANSI_YELLOW + yellowMessage + ANSI_RESET;
    }

    /**
     * Returns a message formatted in blue text.
     *
     * @param blueMessage The message to be formatted in blue.
     * @return The formatted blue message as a string.
     */
    public static String getBlueMessage(String blueMessage) {
        return ANSI_BLUE + blueMessage + ANSI_RESET;
    }

    /**
     * Prints a message to the console in green text.
     *
     * @param greenMessage The message to be printed in green.
     */
    public static void printGreenMessage(String greenMessage) {
        System.out.println(ANSI_GREEN + greenMessage + ANSI_RESET);
    }

    /**
     * Prints a message to the console in yellow text.
     *
     * @param yellowMessage The message to be printed in yellow.
     */
    public static void printYellowMessage(String yellowMessage) {
        System.out.println(ANSI_YELLOW + yellowMessage + ANSI_RESET);
    }
}
