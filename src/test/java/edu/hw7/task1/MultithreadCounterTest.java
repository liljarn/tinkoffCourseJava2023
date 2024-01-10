package edu.hw7.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.assertj.core.api.Assertions.assertThat;

public class MultithreadCounterTest {
    private static final int THREADS_COUNTER = 4;

    @Test
    @DisplayName("Increment test")
    public void increment_shouldReturnCorrectValue() {
        try (ExecutorService executorService = Executors.newFixedThreadPool(THREADS_COUNTER)) {
            CountDownLatch latch = new CountDownLatch(THREADS_COUNTER);
            MultithreadCounter testCounter = new MultithreadCounter();
            for (int i = 0; i < THREADS_COUNTER; i++) {
                executorService.execute(() -> {
                    testCounter.increment();
                    latch.countDown();
                });
            }
            latch.await();
            int actual = testCounter.getCounterValue();
            assertThat(actual).isEqualTo(THREADS_COUNTER);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
