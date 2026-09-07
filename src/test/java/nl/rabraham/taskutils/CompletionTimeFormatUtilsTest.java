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
            0,            0 seconds
            1,            0 seconds
            999,          0 seconds
            1000,         1 seconds
            1999,         1 seconds
            59_000,       59 seconds
            59_999,       59 seconds
            60_000,       1 minutes
            60_001,       1 minutes
            119_999,      1 minutes 59 seconds
            120_000,      2 minutes
            3_599_999,    59 minutes 59 seconds
            3_600_000,    1 hours
            3_600_001,    1 hours
            3_601_000,    1 hours 1 seconds
            3_660_000,    1 hours 1 minutes
            7_199_999,    1 hours 59 minutes 59 seconds
            7_200_000,    2 hours
            """)
    public void testFormatCompletionTimeReturnsFormattedString(long completionTime, String expected) {
        final String actual = CompletionTimeFormatUtils.formatCompletionTime(completionTime);

        assertThat(actual).isEqualTo(expected);
    }
}
