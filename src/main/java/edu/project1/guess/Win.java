package edu.project1.guess;

import org.jetbrains.annotations.NotNull;

public record Win(char[] state, int attempt, int maxAttempts, String answer) implements GuessResult {
    @Override
    public @NotNull String message() {
        return "YOU WIN!!!";
    }
}
