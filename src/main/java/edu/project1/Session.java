package edu.project1;

import edu.project1.guess.Concede;
import edu.project1.guess.Defeat;
import edu.project1.guess.FailedGuess;
import edu.project1.guess.GuessResult;
import edu.project1.guess.SuccessfulGuess;
import edu.project1.guess.Win;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public class Session {
    private final String answer;
    private final char[] userAnswer;
    private final int maxAttempts;
    private GameStatus gameStatus;
    private int attempts;
    private static final char CONCEDE_CHAR = '-';

    public Session(String answer, int maxAttempts) {
        this.gameStatus = GameStatus.RUNNING;
        this.answer = answer;
        this.userAnswer = new char[answer.length()];
        this.maxAttempts = maxAttempts;
        Arrays.fill(userAnswer, '*');
    }

    @NotNull GuessResult guess(char guess) {
        if (answer.indexOf(guess) != -1) {
            for (int i = 0; i < answer.length(); i++) {
                if (answer.charAt(i) == guess) {
                    userAnswer[i] = answer.charAt(i);
                }
            }
            if (new String(userAnswer).equals(answer)) {
                gameStatus = GameStatus.ENDED;
                return new Win(userAnswer, attempts, maxAttempts);
            }
            return new SuccessfulGuess(userAnswer, attempts, maxAttempts);
        }
        attempts++;
        if (guess == CONCEDE_CHAR || attempts == maxAttempts) {
            return giveUp();
        }
        return new FailedGuess(userAnswer, attempts, maxAttempts);
    }

    @NotNull GuessResult giveUp() {
        gameStatus = GameStatus.ENDED;
        if (attempts != maxAttempts) {
            return new Concede(userAnswer, attempts, maxAttempts);
        }
        return new Defeat(userAnswer, attempts, maxAttempts);
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
}
