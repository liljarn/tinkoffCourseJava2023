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
    @DisplayName("Speed approximation PI test with above 100 million iterations")
    public void approximatePiParallel_shouldWorkFasterThan_approximatePi() {
        long nanoStartTimeParallel = System.nanoTime();
        double piParallel = approximatePiParallel(ITERATIONS);
        System.out.println(piParallel);
        long nanoEndTimeParallel = System.nanoTime();
        double parallelTime = (double) (nanoEndTimeParallel - nanoStartTimeParallel) / 1000000000;
        System.out.println(parallelTime + "s");

        long nanoStartTime = System.nanoTime();
        System.out.println(approximatePi(ITERATIONS));
        long nanoEndTime = System.nanoTime();
        double singleTime = (double) (nanoEndTime - nanoStartTime) / 1000000000;
        System.out.println(singleTime + "s");

        System.out.println(Math.abs(Math.PI - piParallel) / Math.PI * 100);
        System.out.println("Параллельная аппроксимация работает быстрее в " + singleTime / parallelTime + " раз");
        assertThat(parallelTime).isLessThan(singleTime);
    }

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
