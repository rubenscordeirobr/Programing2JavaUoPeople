package com.ecommerce.utils;

/**
 * The PrintUtil class provides utility methods for printing messages to the
 * console with colored text, indicating success, failure, warnings, or user
 * input messages. It utilizes ANSI escape codes for text formatting.
 * <p>
 * Note: ANSI color codes may not be supported in all console environments.
 * They work well in most Linux-based terminals and modern IDEs.
 */
public class PrintUtil {

    // ANSI escape code to reset text color to default
    public static final String ANSI_RESET = "\u001B[0m";
    // ANSI escape code for red text color (used for failure messages)
    public static final String ANSI_RED = "\u001B[31m";
    // ANSI escape code for green text color (used for success messages)
    public static final String ANSI_GREEN = "\u001B[32m";
    // ANSI escape code for orange text color (used for warning messages)
    public static final String ANSI_ORANGE = "\u001B[38;2;255;165;0m";
    // ANSI escape code for yellow text color (used for user input messages)
    public static final String ANSI_YELLOW = "\u001B[33m";

    /**
     * Prints a failure message to the console in red text.
     *
     * @param message the failure message to be printed
     */
    public static void printFailMessage(String message) {
        System.out.println(ANSI_RED + message + ANSI_RESET);
    }

    /**
     * Prints a success message to the console in green text.
     *
     * @param message the success message to be printed
     */
    public static void printSuccessMessage(String message) {
        System.out.println(ANSI_GREEN + message + ANSI_RESET);
    }

    /**
     * Prints a warning message to the console in orange text.
     *
     * @param message the warning message to be printed
     */
    public static void printWarningMessage(String message) {
        System.out.println(ANSI_ORANGE + message + ANSI_RESET);
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
     * Prints a message with a selection value to the console in yellow text.
     *
     * @param message   the message to be printed
     * @param selection the selected value to be displayed
     */
    public static void printSelectionMessage(String message, String selection) {
        System.out.print(ANSI_YELLOW + message + ANSI_RESET + ": " + selection);
    }
}
