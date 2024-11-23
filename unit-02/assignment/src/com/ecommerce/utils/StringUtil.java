package com.ecommerce.utils;

/**
 * The StringUtil class provides utility methods for string manipulation, including padding and repeating strings.
 * 
 * This class is designed to simplify common string operations, making code more readable and reusable.
 */
public class StringUtil {

    /**
     * Pads the input string with spaces on the right side until the specified length is reached.
     *
     * @param input  the input string to be padded
     * @param length the desired length of the output string
     * @return the padded string
     */
    public static String padRight(String input, int length) {
        return padRight(input, length, ' ');
    }

    /**
     * Converts the input integer to a string and pads it with spaces on the right side until the specified length is reached.
     *
     * @param input  the integer to be padded
     * @param length the desired length of the output string
     * @return the padded string representation of the integer
     */
    public static String padRight(int input, int length) {
        return padRight(String.valueOf(input), length, ' ');
    }

    /**
     * Converts the input double to a string and pads it with spaces on the right side until the specified length is reached.
     *
     * @param input  the double value to be padded
     * @param length the desired length of the output string
     * @return the padded string representation of the double
     */
    public static String padRight(double input, int length) {
        return padRight(String.valueOf(input), length, ' ');
    }

    /**
     * Pads the input string with the specified character on the right side until the specified length is reached.
     * If the input string is longer than the desired length, it will be truncated and appended with "..".
     *
     * @param input   the input string to be padded
     * @param length  the desired length of the output string
     * @param padChar the character to pad with
     * @return the padded string
     */
    public static String padRight(String input, int length, char padChar) {
        if (input == null) {
            return "";
        }
        // If the input string is longer than the desired length, truncate it and append "..".
        if (input.length() >= length) {
            return input.substring(0, length - 2) + "..";
        }

        StringBuilder sb = new StringBuilder(input);

        // Append padChar until the desired length is reached.
        while (sb.length() < length) {
            sb.append(padChar);
        }

        return sb.toString();
    }
}
