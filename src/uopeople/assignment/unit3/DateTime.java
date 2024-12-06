package uopeople.assignment.unit3;
import uopeople.assignment.unit3.utils.DateFormatterUtil;

/**
 * The DateTime class represents a date and time with components such as
 * year, month, day, hour, minute, and second. It includes utility methods for 
 * formatting the date and time and for comparing instances of DateTime.
 * 
 * This class is immutable, ensuring thread safety and reliability when used 
 * with multithreading.
 */
public class DateTime {

    // Immutable fields representing the components of date and time
    private final int years;
    private final int month;
    private final int days;
    private final int hours;
    private final int minutes;
    private final int seconds;

    /**
     * Constructs a DateTime object with the specified components.
     * 
     * @param years   The year component of the date.
     * @param month   The month component of the date.
     * @param days    The day component of the date.
     * @param hours   The hour component of the time.
     * @param minutes The minute component of the time.
     * @param seconds The second component of the time.
     */
    public DateTime(int years, int month, int days, int hours, int minutes, int seconds) {
        this.years = years;
        this.month = month;
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    /**
     * Formats the date and time according to the specified format string.
     * 
     * @param format A format string defining the output format (e.g., "YYYY-MM-DD HH:MM:SS").
     * @return A formatted string representing the date and time.
     */
    public String format(String format) {
        return DateFormatterUtil.format(this, format);
    }

    // Getters for each component of the date and time

    /**
     * @return The year component of the date.
     */
    public int getYear() {
        return this.years;
    }

    /**
     * @return The month component of the date.
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * @return The day component of the date.
     */
    public int getDay() {
        return this.days;
    }

    /**
     * @return The hour component of the time.
     */
    public int getHour() {
        return this.hours;
    }

    /**
     * @return The minute component of the time.
     */
    public int getMinute() {
        return this.minutes;
    }

    /**
     * @return The second component of the time.
     */
    public int getSecond() {
        return this.seconds;
    }

    /**
     * Provides a default string representation of the DateTime object
     * using the "YYYY-MM-DD HH:MM:SS" format.
     * 
     * @return A string representing the date and time.
     */
    public String toString() {
        return DateFormatterUtil.format(this, "YYYY-MM-DD HH:MM:SS");
    }

    /**
     * Compares this DateTime object with another object for equality.
     * 
     * @param obj The object to compare with.
     * @return true if the objects represent the same date and time, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        DateTime other = (DateTime) obj;

        // Compare all components of the date and time
        return this.years == other.years && this.month == other.month && this.days == other.days
                && this.hours == other.hours && this.minutes == other.minutes && this.seconds == other.seconds;
    }
}