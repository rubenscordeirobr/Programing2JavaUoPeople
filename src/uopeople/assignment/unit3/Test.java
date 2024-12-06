package uopeople.assignment.unit3;

import java.time.ZonedDateTime;
import uopeople.assignment.unit3.utils.DateFormatterUtil;
import uopeople.assignment.utils.AssertUtil;

/**
 * The Test class provides unit tests for the DateTimeClock, DateFormatterUtil, 
 * and DateTime classes. It validates the correctness of formatting, clock functionality, 
 * and date-time conversions.
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        // Run all tests
        testDateFormatterUtil();
        testDateFormatterUtilFromDateTime();
        testClock();
    }

    /**
     * Tests the functionality of the DateTimeClock class by comparing the
     * current local time with the system's current time.
     * 
     * @throws InterruptedException If the thread sleep operation is interrupted.
     */
    public static void testClock() throws InterruptedException {
        Clock clock = new Clock();
        DateTime currentTime = clock.getCurrentLocalDateTime();
        ZonedDateTime currentDate = ZonedDateTime.now();

        // Expected DateTime created from current system time
        DateTime expectedDateTime = new DateTime(
            currentDate.getYear(), 
            currentDate.getMonthValue(), 
            currentDate.getDayOfMonth(),
            currentDate.getHour(), 
            currentDate.getMinute(), 
            currentDate.getSecond()
        );

        AssertUtil.assertEqual(expectedDateTime, currentTime, "DateTimeClock.getCurrentLocalDateTime()");
    }

    /**
     * Tests the formatting capabilities of DateFormatterUtil for various date-time components.
     */
    public static void testDateFormatterUtil() {
        // Test various formatting scenarios

        // Year format (case-insensitive)
        String testYear = DateFormatterUtil.format("YYYY - yyyy", 2021, 12, 31, 23, 59, 59);
        AssertUtil.assertEqual("2021 - 2021", testYear, "DateFormatterUtil.format()");

        // Month format
        String testMonth = DateFormatterUtil.format("MM", 2021, 12, 31, 23, 59, 59);
        AssertUtil.assertEqual("12", testMonth, "DateFormatterUtil.format()");

        // Day format (case-insensitive)
        String testDay = DateFormatterUtil.format("DD - dd", 2021, 12, 31, 23, 59, 59);
        AssertUtil.assertEqual("31 - 31", testDay, "DateFormatterUtil.format()");

        // Hour formats (12-hour and 24-hour, AM/PM)
        String testHours = DateFormatterUtil.format("HH hh a", 2024, 12, 31, 23, 59, 59);
        AssertUtil.assertEqual("23 11 PM", testHours, "DateFormatterUtil.format()");

        String testHours2 = DateFormatterUtil.format("HH hh a", 2024, 12, 31, 11, 59, 59);
        AssertUtil.assertEqual("11 11 AM", testHours2, "DateFormatterUtil.format()");

        // Minutes format
        String testMinutes = DateFormatterUtil.format("mm", 2024, 12, 31, 23, 59, 59);
        AssertUtil.assertEqual("59", testMinutes, "DateFormatterUtil.format()");

        // Seconds format (case-insensitive)
        String testSeconds = DateFormatterUtil.format("SS - ss", 2024, 12, 31, 23, 59, 59);
        AssertUtil.assertEqual("59 - 59", testSeconds, "DateFormatterUtil.format()");

        // Combined formatting
        String testFormatter1 = DateFormatterUtil.format("YYYY-MM-DD HH:mm:SS", 2024, 5, 31, 23, 59, 59);
        AssertUtil.assertEqual("2024-05-31 23:59:59", testFormatter1, "DateFormatterUtil.format()");
    }

    /**
     * Tests the DateTime class's formatting functionality for various scenarios.
     */
    public static void testDateFormatterUtilFromDateTime() {
        DateTime dateTime = new DateTime(2024, 5, 31, 23, 59, 59);
        testDateFormatterUtilFromDateTime(dateTime);

        DateTime dateTime2 = new DateTime(2024, 5, 31, 11, 59, 59);
        testDateFormatterUtilFromDateTime(dateTime2);

        DateTime dateTime3 = new DateTime(2024, 1, 2, 3, 4, 5);
        testDateFormatterUtilFromDateTime(dateTime3);
    }

    /**
     * Tests formatting for a specific DateTime instance.
     * 
     * @param dateTime The DateTime instance to test.
     */
    public static void testDateFormatterUtilFromDateTime(DateTime dateTime) {
        // Year format
        String testYear = dateTime.format("YYYY - yyyy");
        AssertUtil.assertEqual(dateTime.getYear() + " - " + dateTime.getYear(), testYear, "DateTime.format()");

        // Month format
        String testMonth = dateTime.format("MM");
        AssertUtil.assertEqual(String.format("%02d", dateTime.getMonth()), testMonth, "DateTime.format()");

        // Day format
        String testDay = dateTime.format("DD - dd");
        AssertUtil.assertEqual(
            String.format("%02d", dateTime.getDay()) + " - " + String.format("%02d", dateTime.getDay()),
            testDay, 
            "DateTime.format()"
        );

        // Hour formats (12-hour and 24-hour, AM/PM)
        String testHours = dateTime.format("HH hh a");
        String amPm = dateTime.getHour() >= 12 ? "PM" : "AM";
        AssertUtil.assertEqual(
            String.format("%02d", dateTime.getHour()) + " " 
            + String.format("%02d", getHours12(dateTime.getHour())) + " " + amPm, 
            testHours, 
            "DateTime.format()"
        );

        // Minutes format
        String testMinutes = dateTime.format("mm");
        AssertUtil.assertEqual(String.format("%02d", dateTime.getMinute()), testMinutes, "DateTime.format()");

        // Seconds format
        String testSeconds = dateTime.format("SS - ss");
        AssertUtil.assertEqual(
            String.format("%02d", dateTime.getSecond()) + " - " + String.format("%02d", dateTime.getSecond()),
            testSeconds, 
            "DateTime.format()"
        );
    }

    /**
     * Converts 24-hour format to 12-hour format.
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
