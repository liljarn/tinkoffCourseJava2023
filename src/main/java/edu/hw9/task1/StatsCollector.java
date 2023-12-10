package edu.hw9.task1;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class StatsCollector {
    private static final int THREADS = 4;
    private static final int AWAIT_TIME = 5;
    private final ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
    @Getter
    private final Map<String, Statistics> statsMap = new ConcurrentHashMap<>();
    private final BlockingQueue<Metric> metricsQueue = new LinkedBlockingQueue<>();

    public StatsCollector() {
        for (int i = 0; i < THREADS; i++) {
            executorService.execute(this::handleMetric);
        }
    }

    @SneakyThrows
    public void stats() {
        executorService.shutdown();
        executorService.awaitTermination(AWAIT_TIME, TimeUnit.SECONDS);
        for (var entry: statsMap.entrySet()) {
            log.info(entry.getKey() + " " + entry.getValue());
        }
    }

    @SneakyThrows
    public void push(Metric metric) {
        metricsQueue.put(metric);
    }

    @SneakyThrows
    private void handleMetric() {
        while (!executorService.isShutdown()) {
            Metric metric = metricsQueue.take();
            Statistics stat = countStat(metric.data());
            statsMap.put(metric.metricName(), stat);
        }
    }

    private Statistics countStat(double[] data) {
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        double sum = 0;
        for (double datum : data) {
            sum += datum;
            min = Double.min(min, datum);
            max = Double.max(max, datum);
        }
        return new Statistics(sum, sum / data.length, min, max);
    }
}
