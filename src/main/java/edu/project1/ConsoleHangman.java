package edu.project1;

import edu.project1.guess.Concede;
import edu.project1.guess.Defeat;
import edu.project1.guess.GuessResult;
import edu.project1.settings.GameSettings;
import edu.project1.settings.GameWords;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangman {
    private static final int MAX_DIFFICULTY = 4;
    private static final int MAX_LENGTH = 3;
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
        chooseDifficulty(settings);
        LOGGER.info("Type number of chosen word length: 1) 4 letters, 2) 5 letters, 3) 6 letters");
        chooseWordLength(settings);
        return settings;
    }

    private void chooseDifficulty(GameSettings settings) {
        LOGGER.info("Type number of chosen difficulty: 1) easy - 5 attempts, 2) medium - 4 attempts "
            + "3) hard - 3 attempts, 4) death - 1 attempt");
        int difficulty;
        while (true) {
            if (scanner.hasNextInt()) {
                difficulty = scanner.nextInt();
                if (difficulty >= 1 && difficulty <= MAX_DIFFICULTY) {
                    break;
                }
            }
            LOGGER.info("Wrong difficulty");
            scanner.nextLine();
        }
        settings.setGameDifficulty(difficulty);
    }

    private void chooseWordLength(GameSettings settings) {
        int length;
        while (true) {
            if (scanner.hasNextInt()) {
                length = scanner.nextInt();
                if (length >= 1 && length <= MAX_LENGTH) {
                    break;
                }
            }
            LOGGER.info("Wrong word length");
            scanner.nextLine();
        }
        settings.setWordLength(length);
    }

    private GuessResult tryGuess(char input) {
        return session.guess(input);
    }

    private void printState(GuessResult guess) {
        LOGGER.info(guess.message());
        if (guess instanceof Defeat || guess instanceof Concede) {
            LOGGER.info("Your result: " + new String(guess.state()));
            LOGGER.info("The word was: " + guess.answer());
        } else {
            LOGGER.info("The word: " + new String(guess.state()));
        }
    }

    private char inputGuess() {
        LOGGER.info("Guess a letter:");
        while (true) {
            String probableLetter = scanner.nextLine();
            if (probableLetter.length() != 1 || !probableLetter.matches("(^[a-zA-Z\\-]+$)")) {
                LOGGER.info("Please, input only latin characters!");
            } else {
                return probableLetter.toLowerCase().charAt(0);
            }
        }
    }
}
