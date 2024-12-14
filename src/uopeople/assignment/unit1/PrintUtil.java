package uopeople.assignment.unit1;
/**
 * The PrintUtil class provides utility methods for printing messages to the console
 * with colored text to indicate success or failure. It uses ANSI escape codes to
 * format the console output with specific colors.
 */
public class PrintUtil {

    // ANSI escape code to reset text color to default
    public static final String ANSI_RESET = "\u001B[0m";
    // ANSI escape code for red text color (used for failure messages)
    public static final String ANSI_RED = "\u001B[31m";
    // ANSI escape code for green text color (used for success messages)
    public static final String ANSI_GREEN = "\u001B[32m";

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
}
