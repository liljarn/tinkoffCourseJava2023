package edu.hw7.task4;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw7.task4.MonteCarloMethod.approximatePi;
import static edu.hw7.task4.MonteCarloMethod.approximatePiParallel;
import static org.assertj.core.api.Assertions.assertThat;

public class MonteCarloMethodTest {
    private static final long ITERATIONS = 1000000000;

    @Test
    @DisplayName("approximatePi test")
    public void approximatePi_shouldReturnValueClosedToPi() {
        assertThat(approximatePi(ITERATIONS)).isCloseTo(Math.PI, Offset.offset(0.01));
    }

    @Test
    @DisplayName("approximatePiParallel test")
    public void approximatePiParallel_shouldReturnValueClosedToPi() {
        assertThat(approximatePiParallel(ITERATIONS)).isCloseTo(Math.PI, Offset.offset(0.01));
    }
}
