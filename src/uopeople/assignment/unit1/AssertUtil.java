package uopeople.assignment.unit1;
/**
 * A utility class for asserting equality in test cases.
 * This class provides methods to compare expected and actual values of various types
 * (e.g., integers, characters, and strings) and outputs success or failure messages
 * based on the comparison results.
 */
public class AssertUtil {

    /**
     * Asserts that two integers are equal.
     * If they are equal, prints a success message; otherwise, prints a failure message.
     *
     * @param expected   The expected integer value.
     * @param actual     The actual integer value.
     * @param methodName The name of the method being tested (used for output messages).
     */
    public static void assertEqual(int expected, int actual, String methodName) {
        if (expected == actual) {
            PrintUtil.printSuccessMessage(methodName + " passed");
        } else {
            PrintUtil.printFailMessage(methodName + " failed");
        }
    }

    /**
     * Asserts that two characters are equal.
     * If they are equal, prints a success message; otherwise, prints a failure message.
     *
     * @param expected   The expected character value.
     * @param actual     The actual character value.
     * @param methodName The name of the method being tested (used for output messages).
     */
    public static void assertEqual(char expected, char actual, String methodName) {
        if (expected == actual) {
            PrintUtil.printSuccessMessage(methodName + " passed");
        } else {
            PrintUtil.printFailMessage(methodName + " failed");
        }
    }

    /**
     * Asserts that two strings are equal.
     * If they are equal, prints a success message; otherwise, prints a failure message.
     *
     * @param expected   The expected string value.
     * @param actual     The actual string value.
     * @param methodName The name of the method being tested (used for output messages).
     */
    public static void assertEqual(String expected, String actual, String methodName) {
        if (expected.equals(actual)) {
            PrintUtil.printSuccessMessage(methodName + " passed");
        } else {
            PrintUtil.printFailMessage(methodName + " failed");
        }
    }
}
