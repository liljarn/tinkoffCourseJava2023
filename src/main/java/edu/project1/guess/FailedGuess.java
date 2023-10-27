package edu.project1.guess;

import org.jetbrains.annotations.NotNull;

public record FailedGuess(char[] state, int attempt, int maxAttempts, String answer) implements GuessResult {
    @Override
    public @NotNull String message() {
        return String.format("Missed, mistake %d out of %d.", attempt, maxAttempts);
    }
}
