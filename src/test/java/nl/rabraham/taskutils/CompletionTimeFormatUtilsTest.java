package nl.rabraham.taskutils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class CompletionTimeFormatUtilsTest {

    @Test
    public void testFormatCompletionTimeThrowsIllegalArgumentExceptionWhenCompletionTimeIsNegative() {
        assertThatCode(() -> CompletionTimeFormatUtils.formatCompletionTime(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Completion time must be non-negative but was -1");
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            0,            0 minutes 0 seconds
            1,            0 minutes 0 seconds
            999,          0 minutes 0 seconds
            1000,         0 minutes 1 seconds
            1999,         0 minutes 1 seconds
            59_000,       0 minutes 59 seconds
            59_999,       0 minutes 59 seconds
            60_000,       1 minutes 0 seconds
            60_001,       1 minutes 0 seconds
            119_999,      1 minutes 59 seconds
            120_000,      2 minutes 0 seconds
            3_599_999,    59 minutes 59 seconds
            3_600_000,    60 minutes 0 seconds
            """)
    public void testFormatCompletionTimeReturnsFormattedString(long completionTime, String expected) {
        final String actual = CompletionTimeFormatUtils.formatCompletionTime(completionTime);

        assertThat(actual).isEqualTo(expected);
    }
}
