package uopeople.assignment.unit3;

import uopeople.assignment.utils.PrintUtil;

/**
 * The DisplayClock class is responsible for displaying the current local and UTC time,
 * along with the elapsed stopwatch time, at regular intervals.
 * It uses threads to update and display these values continuously.
 */
class DisplayClock implements Runnable {

    private final Clock clock; // Instance of DateTimeClock to fetch local and UTC times
    private final Stopwatch stopwatch; // Stopwatch instance to track elapsed time
    private final int intervalDisplayNewLine; // Interval for printing a new line instead of overwriting

    private int totalSeconds = 0; // Counter for tracking elapsed time in seconds
    private Thread thread; // Thread to run the clock display logic

    /**
     * Constructs a DisplayClock instance with a DateTimeClock and interval for displaying new lines.
     * 
     * @param clock                The DateTimeClock instance providing local and UTC times.
     * @param intervalDisplayNewLine The interval in seconds for printing a new line.
     */
    public DisplayClock(Clock clock, int intervalDisplayNewLine) {
        this.clock = clock;
        this.stopwatch = new Stopwatch();
        this.intervalDisplayNewLine = intervalDisplayNewLine;
    }

    /**
     * Starts the display clock and the stopwatch.
     * Creates a new thread to run the clock display logic.
     */
    public void start() {
        this.thread = new Thread(this);
        this.thread.setPriority(Thread.MAX_PRIORITY); // Set high priority for the display thread
        thread.start();
        this.stopwatch.start();
    }

    /**
     * Continuously displays the local time, UTC time, and elapsed stopwatch time.
     * Formats the output to display either on the same line or on a new line at intervals.
     */
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000); // Pause for 1 second between updates
                this.totalSeconds++;

                // Fetch and format the local and UTC times
                String formattedLocalTime = this.clock.getCurrentLocalDateTime().format("HH:mm:ss dd-MM-yyyy")
                        + " " + this.clock.getLocalGMTOffset();
                String formattedUtcTime = this.clock.getCurrentUTCDateTime().format("HH:mm:ss dd-MM-yyyy");

                // Create formatted messages with color coding
                String localTimeMessage = "(Brazil) Local Time: " + PrintUtil.getGreenMessage(formattedLocalTime);
                String utcTimeMessage = "UTC Time: " + PrintUtil.getYellowMessage(formattedUtcTime);
                String elapsedTimeMessage = "Elapsed Time: " + PrintUtil.getBlueMessage(this.stopwatch.formatElapsedTime());

                // Combine the messages for display
                String fullFormattedMessage = localTimeMessage + " | " + utcTimeMessage + " | " + elapsedTimeMessage;

                // Determine whether to overwrite the same line or print a new line
                if (this.totalSeconds % this.intervalDisplayNewLine == 0) {
                    System.out.print("\r"); // Clear the current line
                    System.out.println(fullFormattedMessage); // Print on a new line
                } else {
                    System.out.print("\r" + fullFormattedMessage); // Overwrite the current line
                }
            } catch (InterruptedException e) {
                e.printStackTrace(); // Handle interruptions gracefully
            }
        }
    }
}
