package edu.project1;

import edu.project1.settings.GameSettings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class GameSettingsTest {
    @ParameterizedTest
    @CsvSource(value = {
        "1, 1, 5, 4",
        "4, 3, 1, 6",
    })
    @DisplayName("Create settings test")
    void settings(int inputDifficulty, int inputLength, int gameDifficulty, int wordLength) {
        GameSettings settings = new GameSettings();
        settings.setGameDifficulty(inputDifficulty);
        settings.setWordLength(inputLength);
        Assertions.assertAll(
            () -> assertThat(settings.getGameDifficulty()).isEqualTo(gameDifficulty),
            () -> assertThat(settings.getWordLength()).isEqualTo(wordLength)
        );
    }
}
