package edu.hw1.task7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw1.task7.CyclicBitShift.rotateLeft;
import static edu.hw1.task7.CyclicBitShift.rotateRight;
import static org.assertj.core.api.Assertions.assertThat;

public class CyclicBitShiftTest {
    @ParameterizedTest
    @CsvSource(value = {
        "17, 2, 6",
        "16, 1, 1",
        "18, 5, 18",
        "31, 10000, 31",
        "5, 4, 3"
    })
    @DisplayName("Тест метода rotateLeft")
    public void rotateLeft_shouldReturnValue(int n, int shift, int expected) {
        assertThat(rotateLeft(n, shift)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "8, 1, 4",
        "17, 3, 6",
        "128, 10000, 128",
        "10, 6, 10"
    })
    @DisplayName("Тест метода rotateLeft")
    public void rotateRight_shouldReturnValue(int n, int shift, int expected) {
        assertThat(rotateRight(n, shift)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Сдвиг меньше нуля")
    public void rotateLeft_shouldReturnMinusOne_whenNegativeShiftInput() {
        int n = 15;
        int shift = -1;
        assertThat(rotateLeft(n, shift)).isEqualTo(-1);
    }

    @Test
    @DisplayName("Введённое число меньнше нуля")
    public void rotateRight_shouldReturnMinusOne_whenNegativeShiftInput() {
        int n = -123;
        int shift = 3;
        assertThat(rotateRight(n, shift)).isEqualTo(-1);
    }
}
