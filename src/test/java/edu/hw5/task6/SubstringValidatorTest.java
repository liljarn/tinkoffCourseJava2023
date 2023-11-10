package edu.hw5.task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.stream.Stream;
import static edu.hw5.task6.SubstringValidator.isSubstring;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SubstringValidatorTest {
    private static Stream<Arguments> textAndItsSubstring() {
        return Stream.of(
            Arguments.of("TORONTOTOKYO", "TOKYO", true),
            Arguments.of("java", "av", true),
            Arguments.of("kiki", "do you love me", false),
            Arguments.of("pyatig", "pyatigorsk", false)
        );
    }

    @ParameterizedTest
    @MethodSource("textAndItsSubstring")
    @DisplayName("Substring validation test")
    public void isSubstring_shouldReturnTrue_whenStringIsASubstringOfGivenText(
        String text,
        String substring,
        boolean isCorrect
    ) {
        assertThat(isSubstring(text, substring)).isEqualTo(isCorrect);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null text test")
    public void isSubstring_shouldThrowException_whenTextIsEmpty(String text) {
        assertThatThrownBy(()->isSubstring(text, "substring")).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null substring test")
    public void isSubstring_shouldThrowException_whenSubstringIsEmpty(String substring) {
        assertThatThrownBy(()->isSubstring("text", substring)).isInstanceOf(IllegalArgumentException.class);
    }
}
