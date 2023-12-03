package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public final class FixedThreadPool implements ThreadPool {
    private final AtomicBoolean canBeExecuted = new AtomicBoolean(false);
    private final BlockingQueue<Runnable> blockingQueue;
    private final ThreadWorker[] threads;

    private FixedThreadPool(int threads) {
        if (threads <= 0) {
            throw new IllegalArgumentException("Pool of threads can't be below zero");
        }
        this.threads = new ThreadWorker[threads];
        blockingQueue = new LinkedBlockingQueue<>();
    }

    public static FixedThreadPool create(int threads) {
        return new FixedThreadPool(threads);
    }

    @Override
    public void start() {
        canBeExecuted.set(true);
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new ThreadWorker();
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (!canBeExecuted.get()) {
            throw new IllegalStateException("ThreadPool not started");
        }
        try {
            blockingQueue.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() {
        canBeExecuted.set(false);
    }

    private final class ThreadWorker extends Thread {
        @Override
        public void run() {
            while (canBeExecuted.get() || !blockingQueue.isEmpty()) {
                Runnable runnable;
                while ((runnable = blockingQueue.poll()) != null) {
                    runnable.run();
                }
            }
        }
    }
}
