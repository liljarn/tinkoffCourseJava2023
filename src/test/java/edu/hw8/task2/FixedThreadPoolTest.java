package edu.hw8.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class FixedThreadPoolTest {
    @Test
    @DisplayName("Counting fibonacci test")
    public void getFib_shouldCalculateFibForDifferentNumbersInMultipleThreads() throws Exception {
        ThreadPool threadPool = FixedThreadPool.create(8);
        List<Integer> expected = List.of(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55);
        threadPool.start();
        final List<Integer> actual = new CopyOnWriteArrayList<>();
        for (int i = 0; i <= 10; i++) {
            final int cur = i;
            threadPool.execute(() -> actual.add(Fibonacci.getFib(cur)));
        }
        threadPool.close();
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }
}
