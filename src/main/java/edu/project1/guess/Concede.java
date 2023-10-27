package edu.project1.guess;

import org.jetbrains.annotations.NotNull;

public record Concede(char[] state, int attempt, int maxAttempts, String answer) implements GuessResult {
    @Override
    public @NotNull String message() {
        return "You've conceded. Good luck next time! Never give up!";
    }
}
