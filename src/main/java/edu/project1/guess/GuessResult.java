package edu.project1.guess;

import org.jetbrains.annotations.NotNull;

sealed public interface GuessResult permits Defeat, Win, SuccessfulGuess, FailedGuess, Concede {
    @NotNull String message();

    char[] state();

    String answer();
}
