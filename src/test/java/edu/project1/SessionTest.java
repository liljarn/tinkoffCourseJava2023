package edu.project1;

import edu.project1.guess.Concede;
import edu.project1.guess.Defeat;
import edu.project1.guess.FailedGuess;
import edu.project1.guess.GuessResult;
import edu.project1.guess.SuccessfulGuess;
import edu.project1.guess.Win;
import edu.project1.settings.GameSettings;
import edu.project1.settings.GameWords;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {
    private final static int HARD_DIFFICULTY = 3;
    private final static int DEATH_DIFFICULTY = 4;
    private final static int LONG_LENGTH = 3;

    @Test
    @DisplayName("Lose with death difficulty test")
    public void sessionGuess_shouldReturnDefeatGuessResult_whenUserIsOutOfMistakes() {
        GameSettings settings = new GameSettings();
        settings.setGameDifficulty(DEATH_DIFFICULTY);
        settings.setWordLength(LONG_LENGTH);
        GameWords wordForGame = new GameWords(settings.getWordLength());
        Session session = new Session(wordForGame.randomWord(), settings.getGameDifficulty());
        GuessResult actual = session.guess('z');
        assertThat(actual).isInstanceOf(Defeat.class);
    }

    @Test
    @DisplayName("Win test")
    public void sessionGuessShouldReturnWinGuessResult_whenUserGuessedTheWord() {
        GameSettings settings = new GameSettings();
        settings.setGameDifficulty(HARD_DIFFICULTY);
        settings.setWordLength(LONG_LENGTH);
        GameWords wordForGame = new GameWords(settings.getWordLength());
        Session session = new Session(wordForGame.randomWord(), settings.getGameDifficulty());
        session.guess('k');
        session.guess('a');
        session.guess('t');
        GuessResult actual = session.guess('n');
        assertThat(actual).isInstanceOf(Win.class);
    }

    @Test
    @DisplayName("Lose with concede test")
    public void sessionGuess_shouldReturnConcedeGuessResult_whenUserSurrendered() {
        GameSettings settings = new GameSettings();
        settings.setGameDifficulty(HARD_DIFFICULTY);
        settings.setWordLength(LONG_LENGTH);
        GameWords wordForGame = new GameWords(settings.getWordLength());
        Session session = new Session(wordForGame.randomWord(), settings.getGameDifficulty());
        GuessResult actual = session.guess('-');
        assertThat(actual).isInstanceOf(Concede.class);
    }
    @Test
    @DisplayName("Game state changing correctly when guess is correct")
    public void sessionStateShouldChangingCorrectly_whenUserHasCorrectGuess() {
        GameSettings settings = new GameSettings();
        settings.setGameDifficulty(HARD_DIFFICULTY);
        settings.setWordLength(LONG_LENGTH);
        GameWords wordForGame = new GameWords(settings.getWordLength());
        Session session = new Session(wordForGame.randomWord(), settings.getGameDifficulty());
        GuessResult failedGuess = session.guess('a');
        Assertions.assertAll(
            () -> assertThat(session.getAttempts()).isEqualTo(0),
            () -> assertThat(failedGuess).isInstanceOf(SuccessfulGuess.class),
            () -> assertThat(failedGuess.state()).containsExactly('*', 'a', '*', 'a', '*', 'a')
        );
    }

    @Test
    @DisplayName("Game state changing correctly when guess is failed")
    public void sessionStateShouldChangingCorrectly_whenUserHasFailedGuess() {
        GameSettings settings = new GameSettings();
        settings.setGameDifficulty(HARD_DIFFICULTY);
        settings.setWordLength(LONG_LENGTH);
        GameWords wordForGame = new GameWords(settings.getWordLength());
        Session session = new Session(wordForGame.randomWord(), settings.getGameDifficulty());
        GuessResult failedGuess = session.guess('z');
        Assertions.assertAll(
            () -> assertThat(session.getAttempts()).isEqualTo(1),
            () -> assertThat(failedGuess).isInstanceOf(FailedGuess.class),
            () -> assertThat(failedGuess.state()).containsExactly('*', '*', '*', '*', '*', '*')
        );
    }
}
