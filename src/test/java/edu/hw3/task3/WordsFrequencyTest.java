package edu.hw3.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static edu.hw3.task3.WordsFrequency.freqDict;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WordsFrequencyTest {
    private static Stream<Arguments> listForTest() {
        return Stream.of(
            Arguments.of(List.of("a", "bb", "a", "bb"), Map.of("a", 2, "bb", 2)),
            Arguments.of(List.of("this", "and", "that", "and"), Map.of("that", 1, "and", 2, "this", 1)),
            Arguments.of(List.of("код", "код", "код", "bug"), Map.of("код", 3, "bug", 1)),
            Arguments.of(List.of(1, 1, 2, 2), Map.of(1, 2, 2, 2)),
            Arguments.of(List.of('j', 'a', 'v', 'a'), Map.of('j', 1, 'a', 2, 'v', 1)),
            Arguments.of(List.of(
                new RecordForTest("Ivan"),
                new RecordForTest("Ivan"),
                new RecordForTest("Anton")),
                Map.of(new RecordForTest("Ivan"), 2,
                        new RecordForTest("Anton"), 1))
        );
    }

    @ParameterizedTest
    @MethodSource("listForTest")
    @DisplayName("Different lists test")
    public <T> void freqDict_shouldReturnMapOfObjectsAndTheirAmount(List<T> list, Map<T, Integer> map) {
        assertThat(freqDict(list)).isEqualTo(map);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Empty or null list test")
    public <T> void freqDict_shouldThrowExceptionWhenListIsEmptyOrNull(List<T> list) {
        assertThatThrownBy(()->freqDict(list)).isInstanceOf(IllegalArgumentException.class);
    }
}
