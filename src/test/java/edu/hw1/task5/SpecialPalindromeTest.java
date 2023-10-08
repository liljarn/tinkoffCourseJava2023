package edu.hw1.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static edu.hw1.task5.SpecialPalindrome.isPalindromeDescendant;
import static org.assertj.core.api.Assertions.assertThat;

public class SpecialPalindromeTest {
    @ParameterizedTest
    @ValueSource(ints = {
        11211230,
        13001120,
        23336014,
        5454
    })
    @DisplayName("Ввод чисел, содержащих в потомках палиндром")
    public void isPalindromeDescendant_shouldReturnTrue_whenInputPalindromeDescendant(int number) {
        assertThat(isPalindromeDescendant(number)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {
        111,
        123321,
        10001,
        9999
    })
    @DisplayName("Ввод чисел, являющихся палиндромом")
    public void isPalindromeDescendant_shouldReturnTrue_whenInputPalindrome(int number) {
        assertThat(isPalindromeDescendant(number)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {
        5459,
        165309,
        123323,
    })
    @DisplayName("Ввод чисел, не являющихся палиндромом и не содержащих в потомках палиндром")
    public void isPalindromeDescendant_shouldReturnFalse_whenInputNotPalindromeDescendant(int number) {
        assertThat(isPalindromeDescendant(number)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {
        -5459,
        -123321,
    })
    @DisplayName("Ввод отрицательных чисел")
    public void isPalindromeDescendant_shouldReturnFalse_whenInputIsNegative(int number) {
        assertThat(isPalindromeDescendant(number)).isFalse();
    }
}
