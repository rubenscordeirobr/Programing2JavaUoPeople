package uopeople.assignment.unit3;

/**
 * The SimpleClockApplication class serves as the entry point for the application.
 * It initializes the DateTimeClock and DisplayClock to showcase real-time clock
 * functionality with periodic updates.
 */
public class SimpleClockApplication {

    public static void main(String[] args) throws InterruptedException {
        // Initialize the clock to track UTC and local date-time
        Clock clock = new Clock();

        // Create a DisplayClock instance to display the date-time information.
        // The DisplayClock updates every second and prints a new line every 5 seconds.
        DisplayClock displayClock = new DisplayClock(clock, 5);

        // Start the DisplayClock
        displayClock.start();
    }
}
