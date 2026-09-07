package nl.rabraham.taskutils;

public enum CompletionTimeFormat {
    /**
     * Formats the completion time as "h hours m minutes s seconds", e.g.: 1 hours 59 minutes 59 seconds.
     */
    LONG("%d hours", "%d minutes","%d seconds"),

    /**
     * Formats the completion time as "hh mm ss", e.g.: 1h 59m 59s.
     */
    SHORT("%dh", "%dm", "%ds");

    private final String hoursFormatString;
    private final String minutesFormatString;
    private final String secondsFormatString;

    CompletionTimeFormat(String hoursFormatString, String minutesFormatString, String secondsFormatString) {
        this.hoursFormatString = hoursFormatString;
        this.minutesFormatString = minutesFormatString;
        this.secondsFormatString = secondsFormatString;
    }

    String getHoursFormatString() {
        return hoursFormatString;
    }

    String getMinutesFormatString() {
        return minutesFormatString;
    }

    String getSecondsFormatString() {
        return secondsFormatString;
    }
}
