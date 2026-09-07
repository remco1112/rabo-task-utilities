package nl.rabraham.taskutils;

import java.time.Duration;
import java.util.StringJoiner;

/**
 * Utility class for formatting completion times to a human-readable format.
 */
public final class CompletionTimeFormatUtils {
    private static final String FORMAT_HOURS = "%d hours";
    private static final String FORMAT_MINUTES = "%d minutes";
    private static final String FORMAT_SECONDS = "%d seconds";

    private CompletionTimeFormatUtils() {

    }

    /**
     * Formats the given completion time in milliseconds to a String of format "h hours m minutes s seconds".
     * <p>
     * Leftover milliseconds are truncated. Zero parts are omitted.
     * Examples:
     * <pre>
     *   <code>toFormattedString(7_199_999)</code> returns <code>1 hours 59 minutes 59 seconds</code>.
     *   <code>toFormattedString(0)</code> returns <code>0 seconds</code>.
     *  </pre>
     *
     * @param completionTimeMillis the completion time in milliseconds
     * @return the formatted string
     * @throws IllegalArgumentException if the completion time is negative
     */
    public static String formatCompletionTime(long completionTimeMillis) {
        if (completionTimeMillis < 0) {
            throw new IllegalArgumentException("Completion time must be non-negative but was " + completionTimeMillis);
        }

        return formatCompletionTime(Duration.ofMillis(completionTimeMillis));
    }

    private static String formatCompletionTime(Duration duration) {
        final StringJoiner joiner = new StringJoiner(" ");
        joiner.setEmptyValue(String.format(FORMAT_SECONDS, 0));

        formatAndJoinIfNonzero(joiner, FORMAT_HOURS, duration.toHours());
        formatAndJoinIfNonzero(joiner, FORMAT_MINUTES, duration.toMinutesPart());
        formatAndJoinIfNonzero(joiner, FORMAT_SECONDS, duration.toSecondsPart());

        return joiner.toString();
    }

    private static void formatAndJoinIfNonzero(StringJoiner dest, String format, long value) {
        if (value != 0) {
            dest.add(String.format(format, value));
        }
    }
}
