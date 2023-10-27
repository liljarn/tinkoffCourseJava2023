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
        GuessResult guess = session.guess('z');
        Assertions.assertAll(
            () -> assertThat(session.getAttempts()).isEqualTo(1),
            () -> assertThat(guess).isInstanceOf(Defeat.class),
            () -> assertThat(guess.state()).containsExactly('*', '*', '*', '*', '*', '*'),
            () -> assertThat(guess.message()).isEqualTo("You lost!"),
            () -> assertThat(session.getGameStatus()).isEqualTo(GameStatus.ENDED)
        );
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
        GuessResult guess = session.guess('n');
        Assertions.assertAll(
            () -> assertThat(session.getAttempts()).isEqualTo(0),
            () -> assertThat(guess).isInstanceOf(Win.class),
            () -> assertThat(guess.state()).containsExactly('k', 'a', 't', 'a', 'n', 'a'),
            () -> assertThat(guess.message()).isEqualTo("YOU WIN!!!"),
            () -> assertThat(session.getGameStatus()).isEqualTo(GameStatus.ENDED)
        );
    }

    @Test
    @DisplayName("Lose with concede test")
    public void sessionGuess_shouldReturnConcedeGuessResult_whenUserSurrendered() {
        GameSettings settings = new GameSettings();
        settings.setGameDifficulty(HARD_DIFFICULTY);
        settings.setWordLength(LONG_LENGTH);
        GameWords wordForGame = new GameWords(settings.getWordLength());
        Session session = new Session(wordForGame.randomWord(), settings.getGameDifficulty());
        GuessResult guess = session.guess('-');
        Assertions.assertAll(
            () -> assertThat(session.getAttempts()).isEqualTo(1),
            () -> assertThat(guess).isInstanceOf(Concede.class),
            () -> assertThat(guess.state()).containsExactly('*', '*', '*', '*', '*', '*'),
            () -> assertThat(guess.message()).isEqualTo("You've conceded. Good luck next time! Never give up!"),
            () -> assertThat(session.getGameStatus()).isEqualTo(GameStatus.ENDED)
        );

    }

    @Test
    @DisplayName("Game state changing correctly when guess is correct")
    public void sessionStateShouldChangingCorrectly_whenUserHasCorrectGuess() {
        GameSettings settings = new GameSettings();
        settings.setGameDifficulty(HARD_DIFFICULTY);
        settings.setWordLength(LONG_LENGTH);
        GameWords wordForGame = new GameWords(settings.getWordLength());
        Session session = new Session(wordForGame.randomWord(), settings.getGameDifficulty());
        GuessResult correctGuess = session.guess('a');
        Assertions.assertAll(
            () -> assertThat(session.getAttempts()).isEqualTo(0),
            () -> assertThat(correctGuess).isInstanceOf(SuccessfulGuess.class),
            () -> assertThat(correctGuess.state()).containsExactly('*', 'a', '*', 'a', '*', 'a'),
            () -> assertThat(correctGuess.message()).isEqualTo("Hit!"),
            () -> assertThat(session.getGameStatus()).isEqualTo(GameStatus.RUNNING)
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
        session.guess('k');
        GuessResult failedGuess = session.guess('x');
        Assertions.assertAll(
            () -> assertThat(session.getAttempts()).isEqualTo(1),
            () -> assertThat(failedGuess).isInstanceOf(FailedGuess.class),
            () -> assertThat(failedGuess.state()).containsExactly('k', '*', '*', '*', '*', '*'),
            () -> assertThat(failedGuess.message()).isEqualTo("Missed, mistake 1 out of 3."),
            () -> assertThat(session.getGameStatus()).isEqualTo(GameStatus.RUNNING)
        );
    }
}
