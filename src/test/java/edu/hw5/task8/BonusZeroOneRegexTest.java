package edu.hw5.task8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.stream.Stream;
import static edu.hw5.task8.BonusZeroOneRegex.doesContainAtLeastTwoZerosAndLessThanTwoOnes;
import static edu.hw5.task8.BonusZeroOneRegex.hasNotTwoOnesInRow;
import static edu.hw5.task8.BonusZeroOneRegex.isEveryOddNumberIsOne;
import static edu.hw5.task8.BonusZeroOneRegex.isLengthOdd;
import static edu.hw5.task8.BonusZeroOneRegex.isNotTwoOrThreeOnesInRow;
import static edu.hw5.task8.BonusZeroOneRegex.isNumberOfZerosDivisibleByThree;
import static edu.hw5.task8.BonusZeroOneRegex.isStartedWithZeroAndOddOrIsStartedWithOneAndEven;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BonusZeroOneRegexTest {
    private static Stream<Arguments> firstRegexTest() {
        return Stream.of(
            Arguments.of("010", true),
            Arguments.of("0", true),
            Arguments.of("11", false),
            Arguments.of("222", false)
        );
    }

    private static Stream<Arguments> secondRegexTest() {
        return Stream.of(
            Arguments.of("10", true),
            Arguments.of("0", true),
            Arguments.of("1011", true),
            Arguments.of("01101", true),
            Arguments.of("100", false),
            Arguments.of("01", false)
        );
    }

    private static Stream<Arguments> thirdRegexTest() {
        return Stream.of(
            Arguments.of("1111", true),
            Arguments.of("000", true),
            Arguments.of("1000111", true),
            Arguments.of("100", false),
            Arguments.of("01", false)
        );
    }

    private static Stream<Arguments> fourthRegexTest() {
        return Stream.of(
            Arguments.of("1111", true),
            Arguments.of("000", true),
            Arguments.of("1000111", true),
            Arguments.of("", true),
            Arguments.of("11", false),
            Arguments.of("111", false)
        );
    }

    private static Stream<Arguments> fifthRegexTest() {
        return Stream.of(
            Arguments.of("10101", true),
            Arguments.of("1010", true),
            Arguments.of("11", false),
            Arguments.of("0001", false)
        );
    }

    private static Stream<Arguments> sixthRegexTest() {
        return Stream.of(
            Arguments.of("001", true),
            Arguments.of("10000", true),
            Arguments.of("000", true),
            Arguments.of("00010000", true),
            Arguments.of("01", false),
            Arguments.of("000111", false),
            Arguments.of("", false)
        );
    }

    private static Stream<Arguments> seventhRegexTest() {
        return Stream.of(
            Arguments.of("10101", true),
            Arguments.of("1", true),
            Arguments.of("0", true),
            Arguments.of("1000", true),
            Arguments.of("10001001", true),
            Arguments.of("11000100000000000000001000000001", false),
            Arguments.of("0000001", true),
            Arguments.of("01010", true),
            Arguments.of("1010", true),
            Arguments.of("0101", true),
            Arguments.of("110101", false),
            Arguments.of("11111", false)
        );
    }

    @ParameterizedTest
    @MethodSource("firstRegexTest")
    @DisplayName("Test of first regex")
    public void isLengthOdd_shouldReturnTrue_whenStringMatchesPattern(String zerosAndOnes, boolean isMatched) {
        assertThat(isLengthOdd(zerosAndOnes)).isEqualTo(isMatched);
    }

    @ParameterizedTest
    @MethodSource("secondRegexTest")
    @DisplayName("Test of second regex")
    public void isStartedWithZeroAndOddOrIsStartedWithOneAndEven_shouldReturnTrue_whenStringMatchesPattern(
        String zerosAndOnes,
        boolean isMatched
    ) {
        assertThat(isStartedWithZeroAndOddOrIsStartedWithOneAndEven(zerosAndOnes)).isEqualTo(isMatched);
    }

    @ParameterizedTest
    @MethodSource("thirdRegexTest")
    @DisplayName("Test of third regex")
    public void isNumberOfZerosDivisibleByThree_shouldReturnTrue_whenStringMatchesPattern(
        String zerosAndOnes,
        boolean isMatched
    ) {
        assertThat(isNumberOfZerosDivisibleByThree(zerosAndOnes)).isEqualTo(isMatched);
    }

    @ParameterizedTest
    @MethodSource("fourthRegexTest")
    @DisplayName("Test of fourth regex")
    public void isNotTwoOrThreeOnesInRow_shouldReturnTrue_whenStringMatchesPattern(
        String zerosAndOnes,
        boolean isMatched
    ) {
        assertThat(isNotTwoOrThreeOnesInRow(zerosAndOnes)).isEqualTo(isMatched);
    }

    @ParameterizedTest
    @MethodSource("fifthRegexTest")
    @DisplayName("Test of fifth regex")
    public void isEveryOddNumberIsOne_shouldReturnTrue_whenStringMatchesPattern(
        String zerosAndOnes,
        boolean isMatched
    ) {
        assertThat(isEveryOddNumberIsOne(zerosAndOnes)).isEqualTo(isMatched);
    }

    @ParameterizedTest
    @MethodSource("sixthRegexTest")
    @DisplayName("Test of sixth regex")
    public void doesContainAtLeastTwoZerosAndLessThanTwoOnes_shouldReturnTrue_whenStringMatchesPattern(
        String zerosAndOnes,
        boolean isMatched
    ) {
        assertThat(doesContainAtLeastTwoZerosAndLessThanTwoOnes(zerosAndOnes)).isEqualTo(isMatched);
    }

    @ParameterizedTest
    @MethodSource("seventhRegexTest")
    @DisplayName("Test of seventh regex")
    public void hasNotTwoOnesInRow_shouldReturnTrue_whenStringMatchesPattern(
        String zerosAndOnes,
        boolean isMatched
    ) {
        assertThat(hasNotTwoOnesInRow(zerosAndOnes)).isEqualTo(isMatched);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Every regex with null source test")
    public void regexes_ShouldThrowException_whenInputIsNull(String nullString) {
        assertAll(
            () -> assertThatThrownBy(() -> isLengthOdd(nullString))
                .isInstanceOf(IllegalArgumentException.class),
            () -> assertThatThrownBy(() -> isStartedWithZeroAndOddOrIsStartedWithOneAndEven(nullString))
                .isInstanceOf(IllegalArgumentException.class),
            () -> assertThatThrownBy(() -> isNumberOfZerosDivisibleByThree(nullString))
                .isInstanceOf(IllegalArgumentException.class),
            () -> assertThatThrownBy(() -> isNotTwoOrThreeOnesInRow(nullString))
                .isInstanceOf(IllegalArgumentException.class),
            () -> assertThatThrownBy(() -> isEveryOddNumberIsOne(nullString))
                .isInstanceOf(IllegalArgumentException.class),
            () -> assertThatThrownBy(() -> doesContainAtLeastTwoZerosAndLessThanTwoOnes(nullString))
                .isInstanceOf(IllegalArgumentException.class),
            () -> assertThatThrownBy(() -> hasNotTwoOnesInRow(nullString))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }
}
