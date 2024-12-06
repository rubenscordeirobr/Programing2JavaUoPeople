package uopeople.assignment.unit3;

/**
 * The Stopwatch class provides functionality to measure elapsed time in seconds.
 * It uses a separate thread to increment the time and supports starting, stopping,
 * and formatting the elapsed time into a human-readable string.
 */
public class Stopwatch {

    // Total elapsed time in seconds
    private int totalSeconds = 0;

    // Indicates if the stopwatch is currently running
    private boolean isRunning = false;

    // Thread responsible for running the stopwatch
    private Thread stopwatchThread;

    /**
     * Starts the stopwatch. If the stopwatch is already running, this method does nothing.
     * A separate thread is used to track elapsed time.
     */
    public void start() {
        if (this.isRunning) {
            return; // Prevent starting if already running
        }

        this.isRunning = true;

        // Create and start a new thread for the stopwatch
        this.stopwatchThread = new Thread(() -> this.run());
        this.stopwatchThread.start();
    }

    /**
     * Private method that runs the stopwatch logic.
     * It increments the total elapsed seconds every second while the stopwatch is running.
     * This method is executed in a separate thread.
     */
    private void run() {
        try {
            while (isRunning) {
                Thread.sleep(1000); // Wait for 1 second
                totalSeconds++; // Increment the elapsed time
            }
        } catch (InterruptedException e) {
            e.printStackTrace(); // Handle interruption (e.g., when the stopwatch is stopped)
        }
    }

    /**
     * Formats the elapsed time into a human-readable string.
     * Examples:
     * - "45 seconds"
     * - "2 minutes 30 seconds"
     * - "1 hour 15 minutes 5 seconds"
     *
     * @return A formatted string representing the elapsed time.
     */
    public String formatElapsedTime() {
        int totalSeconds = this.totalSeconds;

        int hours = totalSeconds / 3600; // Calculate hours
        int minutes = (totalSeconds % 3600) / 60; // Calculate remaining minutes
        int seconds = totalSeconds % 60; // Calculate remaining seconds

        StringBuilder formattedTime = new StringBuilder();

        if (hours > 0) {
            formattedTime.append(hours).append(" hour");
            if (hours > 1) {
                formattedTime.append("s"); // Pluralize hours
            }
            formattedTime.append(" ");
        }

        if (minutes > 0) {
            formattedTime.append(minutes).append(" minute");
            if (minutes > 1) {
                formattedTime.append("s"); // Pluralize minutes
            }
            formattedTime.append(" ");
        }

        if (seconds > 0 || totalSeconds == 0) {
            formattedTime.append(seconds).append(" second");
            if (seconds != 1) {
                formattedTime.append("s"); // Pluralize seconds
            }
        }

        return formattedTime.toString().trim(); // Remove trailing spaces
    }

    /**
     * Stops the stopwatch if it is running.
     * This method interrupts the thread and prevents further time increment.
     */
    public void stop() {
        if (this.isRunning) {
            this.isRunning = false; // Stop the stopwatch
            this.stopwatchThread.interrupt(); // Interrupt the thread
        }
    }
}
