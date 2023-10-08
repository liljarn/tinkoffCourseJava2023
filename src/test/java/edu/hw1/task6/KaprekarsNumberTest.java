package edu.hw1.task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw1.task6.KaprekarsNumber.countK;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class KaprekarsNumberTest {
    @ParameterizedTest
    @CsvSource(value = {
        "6621, 5",
        "6554, 4",
        "1234, 3",
        "6174, 0",
        "8000, 6",
        "7641, 1",
    })
    void countK_shouldReturnValue_whenInputDigits(int number, int operations) {
        assertThat(countK(number)).isEqualTo(operations);
    }

    @Test
    @DisplayName("Ввод числа с четырьмя одинаковыми цифрами")
    public void countK_shouldReturnException_whenInputNumberWithSameDigits() {
        int number = 1111;
        assertThatThrownBy(()->countK(number)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Ввод нечетёрыхзначных чисел")
    public void countK_shouldReturnException_whenInputOutOfBounds() {
        int number = 101;
        assertThatThrownBy(()->countK(number)).isInstanceOf(IllegalArgumentException.class);
    }
}
