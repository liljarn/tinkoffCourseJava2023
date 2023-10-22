package edu.hw3.task8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public class BackwardIteratorTest {
    @Test
    @DisplayName("List test")
    public void list_shouldReturnBackwardList() {
        Iterator<Integer> iterator = new BackwardIterator<>(List.of(1, 2, 3));
        List<Integer> actual = new ArrayList<>();
        while (iterator.hasNext()) {
            actual.add(iterator.next());
        }
        assertThat(actual).containsExactly(3, 2, 1);
    }

    @Test
    @DisplayName("LinkedMap test")
    public void linkedMap_shouldReturnBackwardList() {
        final int[] VALUES = {5, 4, 1};
        final String[] ROMAN_VALUES = {"V", "IV", "I"};
        final Map<Integer, String> ROMAN_DICTIONARY = new LinkedHashMap<>() {{
            for (int i = 0; i < VALUES.length; i++) {
                put(VALUES[i], ROMAN_VALUES[i]);
            }
        }};
        Iterator<Integer> iterator = new BackwardIterator<>(ROMAN_DICTIONARY.keySet().stream().toList());
        List<Integer> backwardList = new ArrayList<>();
        while (iterator.hasNext()) {
            backwardList.add(iterator.next());
        }
        assertThat(backwardList).containsExactly(1, 4, 5);
    }
}
