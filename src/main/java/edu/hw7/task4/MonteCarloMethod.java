package edu.hw7.task4;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

public final class MonteCarloMethod {
    private static final int MONTE_CARLO = 4;
    private static final int THREADS = 8;
    private static final double RADIUS = 0.5;
    private static final double CIRCLE_CENTER = 0.5;

    private MonteCarloMethod() {
    }

    public static double approximatePi(long totalCount) {
        long circleCount = countRandomPointsInCircle(totalCount);
        return (double) (MONTE_CARLO * circleCount) / totalCount;
    }

    public static double approximatePiParallel(long totalCount) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(THREADS)) {
            CountDownLatch countDownLatch = new CountDownLatch(THREADS);
            Future<Long>[] threadsMethod = new Future[THREADS];
            long circleCount = 0;
            for (int i = 0; i < THREADS; i++) {
                threadsMethod[i] = executorService.submit(() -> {
                    long partInCircle = countRandomPointsInCircle(totalCount / THREADS);
                    countDownLatch.countDown();
                    return partInCircle;
                });
            }
            countDownLatch.await();
            for (Future<Long> method : threadsMethod) {
                circleCount += method.get();
            }
            return (double) (MONTE_CARLO * circleCount) / totalCount;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Runtime exception caused");
        }
    }

    private static long countRandomPointsInCircle(long iterationCount) {
        long circleCount = 0;
        for (int i = 0; i < iterationCount; i++) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();
            if (isInCircle(x, y)) {
                circleCount++;
            }
        }
        return circleCount;
    }

    private static boolean isInCircle(double x, double y) {
        return (x - CIRCLE_CENTER) * (x - CIRCLE_CENTER)
            + (y - CIRCLE_CENTER) * (y - CIRCLE_CENTER) <= RADIUS * RADIUS;
    }
}
