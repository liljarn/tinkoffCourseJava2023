package edu.hw3.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import java.util.List;
import java.util.stream.Stream;
import static edu.hw3.task2.BracketsClusterer.clusterize;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BracketsClustererTest {
    private static Stream<Arguments> correctBrackets() {
        return Stream.of(
            Arguments.of("()()()", List.of("()", "()", "()")),
            Arguments.of("((()))", List.of("((()))")),
            Arguments.of("((()))(())()()(()())", List.of("((()))", "(())", "()", "()", "(()())")),
            Arguments.of("((())())(()(()()))", List.of("((())())", "(()(()()))"))
        );
    }

    private static Stream<Arguments> unbalancedBrackets() {
        return Stream.of(
            Arguments.of("(()", "())", ")(", "()))((")
        );
    }

    @ParameterizedTest
    @MethodSource("correctBrackets")
    @DisplayName("Test with correct input")
    public void clusterize_shouldReturnRightCluster(String bracketString, List<String> clusters) {
        assertThat(clusterize(bracketString)).isEqualTo(clusters);
    }

    @ParameterizedTest
    @MethodSource("unbalancedBrackets")
    @DisplayName("Unbalanced brackets test")
    public void clusterize_shouldThrowException_whenBracketsUnbalanced(String bracketString) {
        assertThatThrownBy(() -> clusterize(bracketString)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Wrong input test")
    public void clusterize_shouldThrowException_whenWrongString() {
        String input = "(a)";
        assertThatThrownBy(() -> clusterize(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Null or empty string test")
    public void clusterize_shouldThrowException_whenStringIsNullOrEmpty(String input) {
        assertThatThrownBy(() -> clusterize(input)).isInstanceOf(IllegalArgumentException.class);
    }
}

