package edu.hw10.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FibTest {
    @Test
    @DisplayName("Test for fibonacci function")
    public void fib_shouldReturnCorrectFibonacciNumber() {
        FibCalculator fibCalculator = new Fib();
        assertThat(fibCalculator.fib(9)).isEqualTo(34);
    }
}
