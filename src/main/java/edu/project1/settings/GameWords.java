package edu.project1.settings;

import edu.project1.ConsoleHangman;
import edu.project1.Dictionary;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.jetbrains.annotations.NotNull;

public class GameWords implements Dictionary {
    private final List<String> words = new ArrayList<>();
    private static final int MINIMUM_WORD_LENGTH = 4;
    private static final String[] FILES_WITH_WORDS = {"easyWords.txt", "normalWords.txt", "hardWords.txt"};

    private final int size;

    public GameWords(int size) {
        this.size = size;
    }

    @Override
    public @NotNull String randomWord() {
        fillWordsList();
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }

    private void fillWordsList() {
        InputStream is = ConsoleHangman.class.getResourceAsStream(FILES_WITH_WORDS[size - MINIMUM_WORD_LENGTH]);
        Scanner scanner = new Scanner(is);
        while (scanner.hasNext()) {
            String wordForGame = scanner.nextLine();
            if (isWordCorrectInFile(wordForGame)) {
                words.add(wordForGame.toLowerCase());
            }
        }
    }

    private boolean isWordCorrectInFile(String word) {
        return word.matches("^[a-zA-Z]{" + size + "}$");
    }
}
