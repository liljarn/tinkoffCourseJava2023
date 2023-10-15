package edu.hw1.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw1.task1.VideoLength.minutesToSeconds;
import static org.assertj.core.api.Assertions.assertThat;

public class VideoLengthTest {
    @ParameterizedTest
    @CsvSource(value = {
        "00:00, 0",
        "01:56, 116",
        "20:49, 1249"
    })
    @DisplayName("Корректный ввод")
    void minutesToSeconds_shouldReturnValue_whenCorrectInput(String video, int seconds) {
        assertThat(minutesToSeconds(video)).isEqualTo(seconds);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "10:60, -1",
        "-10:56, -1",
        "100:-14, -1",
        "00:1000, -1",
        "1:56, -1",
        "01:1, -1"
    })
    @DisplayName("Ввод некорректных чисел в строке")
    void minutesToSeconds_shouldReturnValue_whenIncorrectNumbersInput(String video, int seconds) {
        assertThat(minutesToSeconds(video)).isEqualTo(seconds);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "ff:ff, -1",
        "2.51:0.45, -1",
        " : , -1"
    })
    @DisplayName("Ввод неверного типа")
    void minutesToSeconds_shouldReturnValue_whenIncorrectTypeInput(String video, int seconds) {
        assertThat(minutesToSeconds(video)).isEqualTo(seconds);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "10_20, -1",
        "10::20, -1",
        "10: : 20, -1"
    })
    @DisplayName("Ввод с неверным разделителем")
    void minutesToSeconds_shouldReturnValue_whenIncorrectDividerInput(String video, int seconds) {
        assertThat(minutesToSeconds(video)).isEqualTo(seconds);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "10:, -1",
        ":10, -1",
    })
    @DisplayName("Ввод неполной строки")
    void minutesToSeconds_shouldReturnValue_whenNotFullInputString(String video, int seconds) {
        assertThat(minutesToSeconds(video)).isEqualTo(seconds);
    }

    @Test
    @DisplayName("Ввод пустой строки")
    void minutesToSeconds_shouldReturnValue_whenEmptyStringInput() {
        String time = "";
        assertThat(minutesToSeconds(time)).isEqualTo(-1);
    }

    @Test
    @DisplayName("Ввод пустой строки")
    void minutesToSeconds_shouldReturnValue_whenNullStringInput() {
        String time = null;
        assertThat(minutesToSeconds(time)).isEqualTo(-1);
    }
}
