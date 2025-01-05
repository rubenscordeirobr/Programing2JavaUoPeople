package uopeople.assignment.unit8.utils;

/**
 * The TimeUtil class provides utility methods for handling and formatting
 * time-related data.
 * It includes a method to convert a time string in "HHmm" format into a
 * readable "HH:mm" format.
 * 
 */
public class TimeUtil {

    /**
     * Formats a time string from "HHmm" format (e.g., "1430") to "HH:mm" format
     * (e.g., "14:30").
     * 
     * @param time the time string in "HHmm" format.
     * @return the formatted time as a String in "HH:mm" format.
     * @throws NumberFormatException if the input string is not a valid numeric
     *                               time.
     */
    public static String formatTime(String time) {

        int hour = Integer.parseInt(time) / 100;
        int minute = Integer.parseInt(time) % 100;

        return String.format("%02d:%02d", hour, minute);
    }
}
