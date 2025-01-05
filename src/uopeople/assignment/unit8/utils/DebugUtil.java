package uopeople.assignment.unit8.utils;

import java.lang.management.ManagementFactory;

/**
 * The DebugUtil class provides utility methods for checking if a debugger is
 * attached
 * to the current Java process. It is useful for enabling or disabling
 * debug-specific
 * functionality during development or testing.
 */
public class DebugUtil {

    /**
     * Checks if a debugger is attached to the current Java process.
     * This method examines the JVM input arguments for the presence of the
     * "jdwp" (Java Debug Wire Protocol) flag, which indicates a debugger is active.
     * 
     * @return true if a debugger is attached, false otherwise.
     */
    public static boolean isDebuggerAttached() {
        
        // Retrieve the JVM input arguments via the ManagementFactory
        String inputArguments = ManagementFactory.getRuntimeMXBean()
                .getInputArguments()
                .toString();

        // Check if the input arguments contain the "-agentlib:jdwp" flag
        return inputArguments.contains("-agentlib:jdwp");
    }
}
