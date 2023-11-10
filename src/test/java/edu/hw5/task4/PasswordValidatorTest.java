package edu.hw5.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.stream.Stream;
import static edu.hw5.task4.PasswordValidator.isPasswordValid;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordValidatorTest {
    private static Stream<Arguments> correctPasswords() {
        return Stream.of(
            Arguments.of("~aboba"),
            Arguments.of("aboba!"),
            Arguments.of("@@aboba"),
            Arguments.of("###aboba"),
            Arguments.of("zxc$"),
            Arguments.of("qw%e"),
            Arguments.of("kan&eki"),
            Arguments.of("pos1only^"),
            Arguments.of("mmm*"),
            Arguments.of("gho|ul")
        );
    }

    private static Stream<Arguments> wrongPasswords() {
        return Stream.of(
            Arguments.of("aboba"),
            Arguments.of(""),
            Arguments.of("12adsa__")
        );
    }

    @ParameterizedTest
    @MethodSource("correctPasswords")
    @DisplayName("Passwords with required signs test")
    public void isPasswordValid_shouldReturnTrue_whenStringContainsRequiredSign(String signs) {
        assertTrue(isPasswordValid(signs));
    }

    @ParameterizedTest
    @MethodSource("wrongPasswords")
    @DisplayName("Passwords without required signs test")
    public void isPasswordValid_shouldReturnFalse_whenStringDoesNotContainsRequiredSign(String password) {
        assertFalse(isPasswordValid(password));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null input test")
    public void isPasswordValid_shouldThrowException_whenStringIsNull(String nullPassword) {
        assertThatThrownBy(()->isPasswordValid(nullPassword)).isInstanceOf(IllegalArgumentException.class);
    }
}
