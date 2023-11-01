package edu.hw3.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static edu.hw3.task4.RomanNumbers.convertToRoman;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RomanNumbersTest {
    private static Stream<Arguments> roman() {
        return Stream.of(Arguments.of(2, "II"), Arguments.of(12, "XII"),
            Arguments.of(16, "XVI"),
            Arguments.of(49, "XLIX"),
            Arguments.of(588, "DLXXXVIII")
            );
    }

    private static Stream<Arguments> notRoman() {
        return Stream.of(Arguments.of(5000, 0));
    }

    @ParameterizedTest
    @MethodSource("roman")
    @DisplayName("Number bigger than zero but lower than 5000 test")
    public void convertToRoman_shouldReturnRightRomanNumber(int arabic, String roman) {
        assertThat(convertToRoman(arabic)).isEqualTo(roman);
    }

    @ParameterizedTest
    @MethodSource("notRoman")
    @DisplayName("Number lower than zero or bigger than 4999 test")
    public void convertToRoman_shouldThrowException_whenNumberCantBeRoman(int arabic) {
        assertThatThrownBy(()->convertToRoman(arabic)).isInstanceOf(IllegalArgumentException.class);
    }
}
