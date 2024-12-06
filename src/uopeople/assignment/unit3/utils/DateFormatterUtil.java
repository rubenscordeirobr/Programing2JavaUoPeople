package uopeople.assignment.unit3.utils;

import uopeople.assignment.unit3.DateTime;

/**
 * Utility class for formatting DateTime objects into customizable string formats.
 * Supports various placeholders for year, month, day, hour, minute, second, and AM/PM.
 * Provides methods to handle both 24-hour and 12-hour formats.
 */
public class DateFormatterUtil {

    // Case-insensitive regex prefix for replacing placeholders
    private static final String CASE_INSENSITIVE = "(?i)";

    // Constants for placeholder tokens
    private static final String PLACEHOLDER_YEAR = "YYYY"; // Case-insensitive
    private static final String PLACEHOLDER_MONTH = "MM"; // Case-sensitive
    private static final String PLACEHOLDER_DAY = "DD"; // Case-insensitive
    private static final String PLACEHOLDER_HOUR_24 = "HH"; // 24-hour format (Case-sensitive)
    private static final String PLACEHOLDER_HOUR_12 = "hh"; // 12-hour format (Case-sensitive)
    private static final String PLACEHOLDER_MINUTE = "mm"; // Case-insensitive
    private static final String PLACEHOLDER_SECOND = "SS"; // Case-insensitive
    private static final String PLACEHOLDER_AM_PM = "A"; // Case-insensitive for AM/PM marker

    /**
     * Formats a DateTime object into a string using the specified formatter string.
     * 
     * @param dateTime The DateTime object to format.
     * @param formatter A format string containing placeholders (e.g., "YYYY-MM-DD HH:MM:SS").
     * @return The formatted date and time as a string.
     */
    public static String format(DateTime dateTime, String formatter) {
        return format(formatter,
                dateTime.getYear(),
                dateTime.getMonth(),
                dateTime.getDay(),
                dateTime.getHour(),
                dateTime.getMinute(),
                dateTime.getSecond());
    }

    /**
     * Formats the provided date and time components into a string using the specified formatter string.
     * 
     * @param formatter The format string with placeholders for date and time components.
     * @param years The year component.
     * @param month The month component.
     * @param days The day component.
     * @param hours The hour component (24-hour format).
     * @param minutes The minute component.
     * @param seconds The second component.
     * @return The formatted date and time as a string.
     */
    public static String format(String formatter, int years, int month, int days, int hours, int minutes, int seconds) {

        int hours12 = getHours12(hours); // Convert 24-hour format to 12-hour format
        String amPm = hours >= 12 ? "PM" : "AM"; // Determine AM/PM marker

        // Replace placeholders in the formatter with actual values
        formatter = formatter.replaceAll(CASE_INSENSITIVE + PLACEHOLDER_YEAR, String.valueOf(years));
        formatter = formatter.replace(PLACEHOLDER_MONTH, String.format("%02d", month)); // Case-sensitive
        formatter = formatter.replaceAll(CASE_INSENSITIVE + PLACEHOLDER_DAY, String.format("%02d", days));
        formatter = formatter.replace(PLACEHOLDER_HOUR_24, String.format("%02d", hours)); // Case-sensitive
        formatter = formatter.replace(PLACEHOLDER_HOUR_12, String.format("%02d", hours12)); // Case-sensitive
        formatter = formatter.replaceAll(CASE_INSENSITIVE + PLACEHOLDER_MINUTE, String.format("%02d", minutes));
        formatter = formatter.replaceAll(CASE_INSENSITIVE + PLACEHOLDER_SECOND, String.format("%02d", seconds));
        formatter = formatter.replaceAll(CASE_INSENSITIVE + PLACEHOLDER_AM_PM, amPm);

        return formatter;
    }

    /**
     * Converts a 24-hour format hour to a 12-hour format.
     * 
     * @param hours The hour in 24-hour format.
     * @return The hour in 12-hour format.
     */
    private static int getHours12(int hours) {
        if (hours == 0) {
            return 12; // Midnight
        } else if (hours > 12) {
            return hours - 12; // Convert to PM hours
        } else {
            return hours; // Morning hours
        }
    }
}
