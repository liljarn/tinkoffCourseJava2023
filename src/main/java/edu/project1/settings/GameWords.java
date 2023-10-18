package edu.project1.settings;

import edu.project1.Dictionary;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class GameWords implements Dictionary {
    private final static String[][] WORDS = {{"coke", "java"}, {"tokyo", "ghoul"}, {"league", "katana"}};
    private final int size;
    private static final int MINIMUM_WORD_LENGTH = 4;

    public GameWords(int size) {
        this.size = size;
    }

    @Override
    public @NotNull String randomWord() {
        Random random = new Random();
        return WORDS[size % MINIMUM_WORD_LENGTH][random.nextInt(0, WORDS[size % MINIMUM_WORD_LENGTH].length)];
    }
}
