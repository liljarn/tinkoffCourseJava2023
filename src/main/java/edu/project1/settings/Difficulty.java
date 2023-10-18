package edu.project1.settings;

public enum Difficulty {
    EASY(5),
    MEDIUM(4),
    HARD(3),
    DEATH(1);

    private final int difficulty;

    Difficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
