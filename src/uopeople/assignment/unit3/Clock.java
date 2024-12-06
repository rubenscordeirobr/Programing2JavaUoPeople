package uopeople.assignment.unit3;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * The Clock class provides real-time UTC and local date-time tracking.
 * It uses InternalClock instances to simulate clocks for both UTC and local time,
 * offering methods to retrieve current date-time values and the local GMT offset.
 */
public class Clock {

    private final InternalClock utcClock;
    private final InternalClock localClock;
    private final String localGMTOffset;

    private boolean isRunning = false;

    /**
     * Constructs a Clock instance and initializes the clocks for both
     * UTC and local time based on the system's current date-time.
     */
    public Clock() {
        // Get the current UTC and local date-time
        ZonedDateTime currentUtcDateTime = ZonedDateTime.now(ZoneOffset.UTC);

        // Brazil's GMT offset is -3 hours
        //ZonedDateTime currentLocalDateTime =  ZonedDateTime.now(ZoneOffset.ofHours(-3));
        ZonedDateTime currentLocalDateTime =  ZonedDateTime.now();

        // Initialize internal clocks
        this.utcClock = new InternalClock(currentUtcDateTime);
        this.localClock = new InternalClock(currentLocalDateTime);

        // Store the local GMT offset as a string
        this.localGMTOffset = currentLocalDateTime.getOffset().toString();

        // Start both clocks
        this.utcClock.start();
        this.localClock.start();
        this.isRunning = true;
    }

    /**
     * Retrieves the current UTC date-time.
     * 
     * @return A DateTime object representing the current UTC date-time.
     */
    public DateTime getCurrentUTCDateTime() {
        return utcClock.getDateTime();
    }

    /**
     * Retrieves the current local date-time.
     * 
     * @return A DateTime object representing the current local date-time.
     */
    public DateTime getCurrentLocalDateTime() {
        return localClock.getDateTime();
    }

    /**
     * Retrieves the GMT offset for the local clock.
     * 
     * @return A string representing the local GMT offset (e.g., "+02:00").
     */
    public String getLocalGMTOffset() {
        return this.localGMTOffset;
    }

    /**
     * Stops both the UTC and local clocks.
     */
    public void stop() {
        if (this.isRunning) {
            this.utcClock.stop();
            this.localClock.stop();
            this.isRunning = false;
        }
    }
}

/**
 * The InternalClock class simulates a real-time clock for a specific time zone.
 * It continuously updates its internal state to track seconds, minutes, hours, and days.
 */
class InternalClock implements Runnable {

    private int seconds = 0;
    private int minutes = 0;
    private int hours = 0;
    private int days = 0;
    private int month = 0;
    private int years = 0;

    private boolean isRunning = false;
    private Thread thread;

    /**
     * Initializes the clock with a starting date-time.
     * 
     * @param initialDate The initial date-time for the clock.
     */
    public InternalClock(ZonedDateTime initialDate) {
        this.years = initialDate.getYear();
        this.month = initialDate.getMonthValue();
        this.days = initialDate.getDayOfMonth();
        this.hours = initialDate.getHour();
        this.minutes = initialDate.getMinute();
        this.seconds = initialDate.getSecond();
    }

    /**
     * Retrieves the current date-time tracked by the clock.
     * 
     * @return A DateTime object representing the current state of the clock.
     */
    public DateTime getDateTime() {
        return new DateTime(this.years, this.month, this.days, this.hours, this.minutes, this.seconds);
    }

    /**
     * Starts the clock in a separate thread.
     */
    public void start() {
        if (this.isRunning) {
            return;
        }
        this.thread = new Thread(this);
        this.thread.start();
        this.isRunning = true;
    }

    /**
     * Stops the clock and interrupts its thread.
     */
    public void stop() {
        if (this.isRunning) {
            this.isRunning = false;
            this.thread.interrupt();
        }
    }

    /**
     * Updates the clock state every second. Runs in a separate thread.
     */
    public void run() {
        try {
            while (this.isRunning) {
                Thread.sleep(1000); // Sleep for 1 second
                this.seconds++;

                // Update minutes, hours, days, and months as needed
                if (this.seconds == 60) {
                    this.seconds = 0;
                    this.minutes++;
                }
                if (this.minutes == 60) {
                    this.minutes = 0;
                    this.hours++;
                }
                if (this.hours == 24) {
                    this.hours = 0;
                    this.days++;
                }
                if (this.days == this.getDaysOfMonth()) {
                    this.days = 1;
                    this.month++;
                }
                if (this.month == 13) {
                    this.month = 1;
                    this.years++;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace(); // Log interruption
        }
    }

    /**
     * Calculates the number of days in the current month.
     * 
     * @return The number of days in the month.
     */
    private int getDaysOfMonth() {
        switch (this.month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return this.isLeapYear() ? 29 : 28;
            default:
                return 0;
        }
    }

    /**
     * Determines if the current year is a leap year.
     * 
     * @return True if the year is a leap year, false otherwise.
     */
    private boolean isLeapYear() {
        return (this.years % 4 == 0 && this.years % 100 != 0) || (this.years % 400 == 0);
    }
}
