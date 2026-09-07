package nl.rabraham.taskutils;

import java.time.Duration;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Utility class for formatting completion times to a human-readable format.
 */
public final class CompletionTimeFormatUtils {

    private CompletionTimeFormatUtils() {

    }

    /**
     * Formats the given completion time in milliseconds to a String describing the time in hours, minutes and seconds.
     * <p>
     * The format of the hours, minutes, and seconds part is determined by the given {@link CompletionTimeFormat}.
     * Leftover milliseconds are truncated. Zero parts are omitted.
     * Examples:
     * <pre>
     *   <code>toFormattedString(7_199_999, CompletionTimeFormat.LONG)</code> returns <code>1 hours 59 minutes 59 seconds</code>.
     *   <code>toFormattedString(0, CompletionTimeFormat.LONG)</code> returns <code>0 seconds</code>.
     *   <code>toFormattedString(7_199_999, CompletionTimeFormat.SHORT)</code> returns <code>1h 59m 59s</code>.
     *  </pre>
     *
     * @param completionTimeMillis the completion time in milliseconds
     * @param format               the format to use
     * @return the formatted string
     * @throws IllegalArgumentException if the completion time is negative
     */
    public static String formatCompletionTime(long completionTimeMillis, CompletionTimeFormat format) {
        Objects.requireNonNull(format, "format must not be null");

        if (completionTimeMillis < 0) {
            throw new IllegalArgumentException("Completion time must be non-negative but was " + completionTimeMillis);
        }

        return formatCompletionTime(Duration.ofMillis(completionTimeMillis), format);
    }

    /**
     * Formats the given completion time in milliseconds to a String of format "h hours m minutes s seconds".
     * <p>
     * This is equivalent to calling {@link #formatCompletionTime(long, CompletionTimeFormat)} with the LONG format.
     *
     * @param completionTimeMillis the completion time in milliseconds
     * @return the formatted string
     * @see #formatCompletionTime(long, CompletionTimeFormat)
     */
    public static String formatCompletionTime(long completionTimeMillis) {
        return formatCompletionTime(completionTimeMillis, CompletionTimeFormat.LONG);
    }

    private static String formatCompletionTime(Duration duration, CompletionTimeFormat format) {
        final StringJoiner joiner = new StringJoiner(" ");
        joiner.setEmptyValue(String.format(format.getSecondsFormatString(), 0));

        formatAndJoinIfNonzero(joiner, format.getHoursFormatString(), duration.toHours());
        formatAndJoinIfNonzero(joiner, format.getMinutesFormatString(), duration.toMinutesPart());
        formatAndJoinIfNonzero(joiner, format.getSecondsFormatString(), duration.toSecondsPart());

        return joiner.toString();
    }

    private static void formatAndJoinIfNonzero(StringJoiner dest, String format, long value) {
        if (value != 0) {
            dest.add(String.format(format, value));
        }
    }
}
