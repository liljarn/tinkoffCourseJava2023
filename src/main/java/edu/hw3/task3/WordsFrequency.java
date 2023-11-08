package edu.hw3.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class WordsFrequency {
    private WordsFrequency() {
    }

    public static <T> Map<T, Integer> freqDict(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Empty or null list");
        }
        Map<T, Integer> frequencyDictionary = new HashMap<>();
        for (T item: list) {
            if (!frequencyDictionary.containsKey(item)) {
                frequencyDictionary.put(item, 1);
            } else {
                frequencyDictionary.put(item, frequencyDictionary.get(item) + 1);
            }
        }
        return frequencyDictionary;
    }
}
