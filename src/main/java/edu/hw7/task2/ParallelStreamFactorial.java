package edu.hw7.task2;

import java.util.stream.IntStream;

public final class ParallelStreamFactorial {
    private ParallelStreamFactorial() {
    }

    public static int getFactorial(int n) {
        return IntStream.rangeClosed(1, n).parallel().reduce((a, b) -> a * b).getAsInt();
    }
}
