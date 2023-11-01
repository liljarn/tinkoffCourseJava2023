package edu.hw3.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static edu.hw3.task1.AtbashCipher.atbash;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AtbashCipherTest {
    static Arguments[] text() {
        return new Arguments[] {Arguments.of("Hello world!", "Svool dliow!"),
            Arguments.of(
                "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler",
                "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi"
            )};
    }

    @ParameterizedTest
    @MethodSource("text")
    @DisplayName("String with lower and upper cases letters and cymbals")
    public void shouldReturnCorrectCipheredString(String str, String actual) {
        assertThat(atbash(str)).isEqualTo(actual);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Empty and null string test")
    public void  shouldThrowExceptionWhenNullOrEmptyStringInput(String str) {
        assertThatThrownBy(() -> atbash(str)).isInstanceOf(IllegalArgumentException.class);
    }
}
