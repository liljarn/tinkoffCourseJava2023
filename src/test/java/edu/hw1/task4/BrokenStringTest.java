package edu.hw1.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw1.task4.BrokenString.fixString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BrokenStringTest {
    @ParameterizedTest
    @CsvSource(value = {
        "123456, 214365",
        "hTsii  s aimex dpus rtni.g, This is a mixed up string.",
        "badce, abcde",
        "baboa, aboba"
    })
    @DisplayName("Корректный ввод")
    public void fixString_shouldReturnValue_whenCorrectInput(String brokenString, String fixedString) {
        assertThat(fixString(brokenString)).isEqualTo(fixedString);
    }

    @Test
    @DisplayName("Ввод пустой строки")
    public void fixString_shouldReturnException_whenInputEmpty() {
        String brokenString = "";
        assertThatThrownBy(()->fixString(brokenString)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Ввод нулево строки")
    public void fixString_shouldReturnException_whenInputNull() {
        String brokenString = null;
        assertThatThrownBy(()->fixString(brokenString)).isInstanceOf(NullPointerException.class);
    }
}
