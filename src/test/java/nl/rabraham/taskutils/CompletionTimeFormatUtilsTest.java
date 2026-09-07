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
            60_000,       60 seconds
            """)
    public void testFormatCompletionTimeReturnsFormattedString(long completionTime, String expected) {
        final String actual = CompletionTimeFormatUtils.formatCompletionTime(completionTime);

        assertThat(actual).isEqualTo(expected);
    }
}
