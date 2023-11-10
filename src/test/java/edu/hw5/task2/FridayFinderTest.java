package edu.hw5.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import static edu.hw5.task2.FridayFinder.findAllFridays13thInOneYear;
import static edu.hw5.task2.FridayFinder.findNextFriday13th;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FridayFinderTest {
    private static Stream<Arguments> yearsForFridayFinderTest() {
        return Stream.of(
            Arguments.of(
                1925,
                List.of(
                    LocalDate.of(1925, 2, 13),
                    LocalDate.of(1925, 3, 13),
                    LocalDate.of(1925, 11, 13)
                )
            ),
            Arguments.of(
                2024,
                List.of(
                    LocalDate.of(2024, 9, 13),
                    LocalDate.of(2024, 12, 13)
                )
            )
        );
    }

    private static Stream<Arguments> datesForNextFridayFinderTest() {
        return Stream.of(
            Arguments.of(
                LocalDate.of(1925, 1, 13),
                LocalDate.of(1925, 2, 13)
            ),
            Arguments.of(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 13)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("yearsForFridayFinderTest")
    @DisplayName("Finder of fridays 13th in correct years test")
    public void findAllFridays13thInOneYear_shouldReturnListOfAllFridays(int year, List<LocalDate> fridays) {
        assertThat(findAllFridays13thInOneYear(year)).isEqualTo(fridays);
    }

    @Test
    @DisplayName("Finder of fridays 13th in wrong year test")
    public void findAllFridays13thInOneYear_shouldThrowException_whenYearBelowOne() {
        assertThatThrownBy(() -> findAllFridays13thInOneYear(-1)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("datesForNextFridayFinderTest")
    @DisplayName("Finder of next friday 13th test")
    public void findNextFriday13th_shouldReturnNextFriday13th(LocalDate date, LocalDate nextFriday) {
        assertThat(findNextFriday13th(date)).isEqualTo(nextFriday);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Finder of next friday 13th with null input test")
    public void findNextFriday13th_shouldThrowException_whenDateIsNull(LocalDate date) {
        assertThatThrownBy(() -> findNextFriday13th(date)).isInstanceOf(IllegalArgumentException.class);
    }
}
