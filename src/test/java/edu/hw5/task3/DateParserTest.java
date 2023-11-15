package edu.hw5.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;
import static edu.hw5.task3.DateParser.parseDate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateParserTest {
    private static Stream<Arguments> differentFormatsOfDate() {
        return Stream.of(
            Arguments.of("2020-10-10", LocalDate.of(2020, 10, 10)),
            Arguments.of("2020-12-2", LocalDate.of(2020, 12, 2)),
            Arguments.of("1/3/1976", LocalDate.of(1976, 3, 1)),
            Arguments.of("1/3/20", LocalDate.of(2020, 3, 1)),
            Arguments.of("tomorrow", LocalDate.now().plusDays(1)),
            Arguments.of("today", LocalDate.now()),
            Arguments.of("yesterday", LocalDate.now().minusDays(1)),
            Arguments.of("1 day ago", LocalDate.now().minusDays(1)),
            Arguments.of("2234 days ago", LocalDate.now().minusDays(2234))
        );
    }

    @ParameterizedTest
    @MethodSource("differentFormatsOfDate")
    @DisplayName("Different dates format test test")
    public void parseDate_shouldReturnCorrectParsedDate(String testString, LocalDate expected) {
        Optional<LocalDate> actual = parseDate(testString);
        assertThat(actual.get()).isEqualTo(expected);
    }

    @Test
    @DisplayName("Incorrect date format test")
    public void parseDate_shouldReturnEmptyOptional_whenStringHasWrongFormat() {
        Optional<LocalDate> date = parseDate("2021/10-10");
        assertTrue(date.isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("#parseDate null and empty test")
    public void parseDate_shouldThrowExceptionWhenStringIsNullOrEmpty(String testString) {
        assertThatThrownBy(() -> parseDate(testString)).isInstanceOf(IllegalArgumentException.class);
    }
}
