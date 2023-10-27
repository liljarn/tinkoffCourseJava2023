package edu.project1.guess;

import org.jetbrains.annotations.NotNull;

public record Defeat(char[] state, int attempt, int maxAttempts, String answer) implements GuessResult {
    @Override
    public @NotNull String message() {
        return "You lost!";
    }
}
