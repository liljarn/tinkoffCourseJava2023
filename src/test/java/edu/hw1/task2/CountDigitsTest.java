package edu.hw1.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw1.task2.CountDigits.countDigits;
import static org.assertj.core.api.Assertions.assertThat;

public class CountDigitsTest {
    @ParameterizedTest
    @CsvSource(value = {
        "10, 2",
        "111, 3",
        "17890, 5",
    })
    @DisplayName("Положительные числа")
    void countDigits_shouldReturnValue_whenInputPositiveNumbers(int number, int answer) {
        assertThat(countDigits(number)).isEqualTo(answer);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "-10, 2",
        "-111, 3",
        "-17890, 5",
    })
    @DisplayName("Отрицательные числа")
    void countDigits_shouldReturnValue_whenInputNegativeNumbers(int number, int answer) {
        assertThat(countDigits(number)).isEqualTo(answer);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "00, 1",
        "0, 1",
        "00000, 1",
    })
    @DisplayName("Ввод нуля")
    void countDigits_shouldReturnValue_whenInputZeroNumber(int number, int answer) {
        assertThat(countDigits(number)).isEqualTo(answer);
    }
}
