package edu.project1.settings;

public class GameSettings {
    private int maxAttempts;
    private int wordLength;
    private final static int EASY_DIFFICULTY = 1;
    private final static int MEDIUM_DIFFICULTY = 2;
    private final static int HARD_DIFFICULTY = 3;
    private final static int DEATH_DIFFICULTY = 4;
    private final static int SHORT_LENGTH = 4;
    private final static int MEDIUM_LENGTH = 5;
    private final static int LONG_LENGTH = 6;

    public void setGameDifficulty(int difficulty) {
        switch (difficulty) {
            case EASY_DIFFICULTY:
                maxAttempts = Difficulty.EASY.getDifficulty();
                break;
            case MEDIUM_DIFFICULTY:
                maxAttempts = Difficulty.MEDIUM.getDifficulty();
                break;
            case HARD_DIFFICULTY:
                maxAttempts = Difficulty.HARD.getDifficulty();
                break;
            case DEATH_DIFFICULTY:
                maxAttempts = Difficulty.DEATH.getDifficulty();
                break;
            default:
                maxAttempts = Difficulty.MEDIUM.getDifficulty();
                break;
        }
    }

    public void setWordLength(int length) {
        switch (length) {
            case SHORT_LENGTH:
                wordLength = WordLength.SHORT.getLength();
                break;
            case MEDIUM_LENGTH:
                wordLength = WordLength.MEDIUM.getLength();
                break;
            case LONG_LENGTH:
                wordLength = WordLength.LONG.getLength();
                break;
            default:
                wordLength = WordLength.SHORT.getLength();
                break;
        }
    }

    public int getGameDifficulty() {
        return maxAttempts;
    }

    public int getWordLength() {
        return wordLength;
    }
}
