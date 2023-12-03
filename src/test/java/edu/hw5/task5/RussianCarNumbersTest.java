package edu.hw5.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.stream.Stream;
import static edu.hw5.task5.RussianCarNumbers.isNumberValid;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
public class RussianCarNumbersTest {
    private static Stream<Arguments> carNumbersForTest() {
        return Stream.of(
            Arguments.of("А123ВЕ777", true),
            Arguments.of("О777ОО177", true),
            Arguments.of("123АВЕ777", false),
            Arguments.of("А123ВЕ77", false),
            Arguments.of("А123ВЕ7777", false)
        );
    }
    @ParameterizedTest
    @MethodSource("carNumbersForTest")
    public void isNumberValid_shouldReturnTrue_whenNumberIsValid(String carNumber, boolean isValid) {
        assertThat(isNumberValid(carNumber)).isEqualTo(isValid);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null input test")
    public void isisNumberValid_shouldThrowException_whenStringIsNull(String nullNumber) {
        assertThatThrownBy(()->isNumberValid(nullNumber)).isInstanceOf(IllegalArgumentException.class);
    }
}
