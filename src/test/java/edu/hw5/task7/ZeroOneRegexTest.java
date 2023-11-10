package edu.hw5.task7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.stream.Stream;
import static edu.hw5.task7.ZeroOneRegex.isMatchesFirstPatten;
import static edu.hw5.task7.ZeroOneRegex.isMatchesSecondPatten;
import static edu.hw5.task7.ZeroOneRegex.isMatchesThirdPatten;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZeroOneRegexTest {
    private static Stream<Arguments> firstRegexCorrectInputTest() {
        return Stream.of(
            Arguments.of("010"),
            Arguments.of("000"),
            Arguments.of("110"),
            Arguments.of("11010101010")
        );
    }

    private static Stream<Arguments> firstRegexIncorrectInputTest() {
        return Stream.of(
            Arguments.of("011"),
            Arguments.of("001"),
            Arguments.of("111"),
            Arguments.of("11110101010"),
            Arguments.of("00"),
            Arguments.of(""),
            Arguments.of("a10"),
            Arguments.of("210")
        );
    }

    private static Stream<Arguments> secondRegexCorrectInputTest() {
        return Stream.of(
            Arguments.of("00"),
            Arguments.of("11"),
            Arguments.of("1010101010011"),
            Arguments.of("0010101010010")
        );
    }

    private static Stream<Arguments> secondRegexIncorrectInputTest() {
        return Stream.of(
            Arguments.of("1"),
            Arguments.of("0"),
            Arguments.of("10"),
            Arguments.of("01"),
            Arguments.of("11110101010"),
            Arguments.of("0110101010101"),
            Arguments.of("022220"),
            Arguments.of("0aaaaa0"),
            Arguments.of("")
        );
    }

    private static Stream<Arguments> thirdRegexCorrectInputTest() {
        return Stream.of(
            Arguments.of("0"),
            Arguments.of("1"),
            Arguments.of("01"),
            Arguments.of("10"),
            Arguments.of("000")
        );
    }

    private static Stream<Arguments> thirdRegexIncorrectInputTest() {
        return Stream.of(
            Arguments.of("1000"),
            Arguments.of("100111000"),
            Arguments.of("02"),
            Arguments.of("0a"),
            Arguments.of("")
        );
    }

    @ParameterizedTest
    @MethodSource("firstRegexCorrectInputTest")
    @DisplayName("Correct string format for first regex test")
    public void isMatchesFirstPattern_shouldReturnTrue_whenStringMatchesRegex(String zerosAndOnes) {
        assertTrue(isMatchesFirstPatten(zerosAndOnes));
    }

    @ParameterizedTest
    @MethodSource("firstRegexIncorrectInputTest")
    @DisplayName("Incorrect string format for first regex test")
    public void isMatchesFirstPattern_shouldReturnFalse_whenStringDoesntMatchRegex(String zerosAndOnes) {
        assertFalse(isMatchesFirstPatten(zerosAndOnes));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null string for first regex test")
    public void isMatchesFirstPattern_shouldThrowException_whenStringIsNull(String zerosAndOnes) {
        assertThatThrownBy(() -> isMatchesFirstPatten(zerosAndOnes)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("secondRegexCorrectInputTest")
    @DisplayName("Correct string format for second regex test")
    public void isMatchesSecondPattern_shouldReturnTrue_whenStringMatchesRegex(String zerosAndOnes) {
        assertTrue(isMatchesSecondPatten(zerosAndOnes));
    }

    @ParameterizedTest
    @MethodSource("secondRegexIncorrectInputTest")
    @DisplayName("Incorrect string format for second regex test")
    public void isMatchesSecondPattern_shouldReturnFalse_whenStringDoesntMatchRegex(String zerosAndOnes) {
        assertFalse(isMatchesSecondPatten(zerosAndOnes));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null string for second regex test")
    public void isMatchesSecondPattern_shouldThrowException_whenStringIsNull(String zerosAndOnes) {
        assertThatThrownBy(() -> isMatchesSecondPatten(zerosAndOnes)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("thirdRegexCorrectInputTest")
    @DisplayName("Correct string format for third regex test")
    public void isMatchesThirdPattern_shouldReturnTrue_whenStringMatchesRegex(String zerosAndOnes) {
        assertTrue(isMatchesThirdPatten(zerosAndOnes));
    }

    @ParameterizedTest
    @MethodSource("thirdRegexIncorrectInputTest")
    @DisplayName("Incorrect string format for third regex test")
    public void isMatchesThirdPattern_shouldReturnFalse_whenStringDoesntMatchRegex(String zerosAndOnes) {
        assertFalse(isMatchesThirdPatten(zerosAndOnes));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null string for third regex test")
    public void isMatchesThirdPattern_shouldThrowException_whenStringIsNull(String zerosAndOnes) {
        assertThatThrownBy(() -> isMatchesThirdPatten(zerosAndOnes)).isInstanceOf(IllegalArgumentException.class);
    }
}
