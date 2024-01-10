package edu.hw9.task1;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class StatsCollectorTest {
    private static Stream<Arguments> getRandomArrays() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new double[] {
                        1.4227862056379224, 3.8915594443087858
                    },
                    new double[] {
                        6.963154129102754,
                        10.474060354758558,
                        1.5696040342882331,
                        13.181157353562075,
                        13.886806477917686,
                        16.28991711008446,
                        13.235267135709865,
                        7.243543464564334,
                        12.494498878531969
                    },
                    new double[] {
                        13.04932438697816,
                        10.157264735839682,
                        4.099348901197324,
                        2.985494572780239,
                        14.026869308892874
                    }
                ),
                Map.of("Name 0",
                    new Statistics(5.314345649946708, 2.657172824973354, 1.4227862056379224, 3.8915594443087858),
                    "Name 1",
                    new Statistics(95.33800893851993, 10.593112104279992, 1.5696040342882331, 16.28991711008446),
                    "Name 2",
                    new Statistics(44.31830190568828, 8.863660381137656, 2.985494572780239, 14.026869308892874)
                )
            ));
    }

    @ParameterizedTest
    @MethodSource("getRandomArrays")
    @DisplayName("StatsCollector general test")
    @SneakyThrows
    public void statsCollector_shouldProcessAllSentMetrics(List<double[]> values, Map<String, Statistics> expected) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        StatsCollector collector = new StatsCollector();
        for (int i = 0; i < 3; i++) {
            final int copy = i;
            executorService.submit(() -> collector.push(new Metric("Name " + copy, values.get(copy))));
        }
        Thread.sleep(1);
        collector.stats();
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        assertThat(collector.getStatsMap()).containsExactlyInAnyOrderEntriesOf(expected);
    }
}
