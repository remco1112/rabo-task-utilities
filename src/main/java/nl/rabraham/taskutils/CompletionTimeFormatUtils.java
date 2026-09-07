package nl.rabraham.taskutils;

import java.time.Duration;

/**
 * Utility class for formatting completion times to a human-readable format.
 */
public final class CompletionTimeFormatUtils {
    private static final String COMPLETION_TIME_FORMAT = "%d seconds";

    private CompletionTimeFormatUtils() {

    }

    /**
     * Formats the given completion time in milliseconds to a String of format "s seconds".
     * <p>
     * Leftover milliseconds are truncated. For example, <code>toFormattedString(10145)</code>
     * returns <code>10 seconds</code>.
     *
     * @param completionTimeMillis the completion time in milliseconds
     * @throws IllegalArgumentException if the completion time is negative
     * @return the formatted string
     */
    public static String formatCompletionTime(long completionTimeMillis) {
        if (completionTimeMillis < 0) {
            throw new IllegalArgumentException("Completion time must be non-negative but was " + completionTimeMillis);
        }

        return formatCompletionTime(Duration.ofMillis(completionTimeMillis));
    }

    private static String formatCompletionTime(Duration duration) {
        return String.format(COMPLETION_TIME_FORMAT, duration.getSeconds());
    }
}
