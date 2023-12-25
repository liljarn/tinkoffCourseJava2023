package edu.hw7.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static edu.hw7.task2.ParallelStreamFactorial.getFactorial;
import static org.assertj.core.api.Assertions.assertThat;

public class ParallelStreamFactorialTest {
    private static Stream<Arguments> factorials() {
        return Stream.of(
            Arguments.of(5, 120),
            Arguments.of(4, 24),
            Arguments.of(6, 720),
            Arguments.of(10, 3628800)
        );
    }

    @ParameterizedTest
    @MethodSource("factorials")
    @DisplayName("getFactorial test")
    public void getFactorial_ShouldReturnCorrectValue(int factorial, int factorialValue) {
        assertThat(getFactorial(factorial)).isEqualTo(factorialValue);
    }
}
