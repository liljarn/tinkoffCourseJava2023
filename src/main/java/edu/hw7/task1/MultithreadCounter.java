package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicInteger;

public class MultithreadCounter {
    private final AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        counter.incrementAndGet();
    }

    public int getCounterValue() {
        return counter.get();
    }
}
