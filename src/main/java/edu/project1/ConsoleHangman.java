package edu.project1;

import edu.project1.guess.GuessResult;
import edu.project1.settings.GameSettings;
import edu.project1.settings.GameWords;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangman {
    private static final Logger LOGGER = LogManager.getLogger();
    private Session session;
    private final Scanner scanner;

    public ConsoleHangman() {
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        LOGGER.info("Welcome to the Hangman game!");
        GameSettings settings = setSettings();
        session = createSession(settings);
        LOGGER.info("If you want to concede: type '-'");
        while (!session.getGameStatus().equals(GameStatus.ENDED)) {
            char usersLetter = inputGuess();
            printState(tryGuess(usersLetter));
        }
    }

    private Session createSession(GameSettings settings) {
        GameWords wordForGame = new GameWords(settings.getWordLength());
        return new Session(wordForGame.randomWord(), settings.getGameDifficulty());
    }

    private GameSettings setSettings() {
        GameSettings settings = new GameSettings();
        LOGGER.info("Type number of chosen difficulty: 1: easy, 2: medium 3: hard, 4: death");
        int difficulty = scanner.nextInt();
        settings.setGameDifficulty(difficulty);
        LOGGER.info("Type number of chosen word length: 4, 5 or 6");
        int length = scanner.nextInt();
        settings.setWordLength(length);
        return settings;
    }

    private GuessResult tryGuess(char input) {
        return session.guess(input);
    }

    private void printState(GuessResult guess) {
        LOGGER.info(guess.message());
        LOGGER.info("The word: " + new String(guess.state()));
    }

    private char inputGuess() {
        LOGGER.info("Guess a letter:");
        String probableLetter = scanner.next();
        return probableLetter.charAt(0);
    }
}
