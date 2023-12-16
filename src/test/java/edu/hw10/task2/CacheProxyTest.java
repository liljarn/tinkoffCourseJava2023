package edu.hw10.task2;

import edu.hw10.task2.proxy.CacheProxy;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class CacheProxyTest {
    private final Path path = Path.of("src", "main", "resources", "hw10", "cached", "fib_num.txt");
    private final Path dirPath = Path.of( "src", "main", "resources", "hw10", "cached");

    @SneakyThrows
    @BeforeEach
    void deleteDir() {
        Files.deleteIfExists(path);
        Files.deleteIfExists(dirPath);
    }

    @SneakyThrows
    @Test
    @DisplayName("CacheProxy test with Cache (persist=true) annotation")
    public void proxyFib_shouldSaveResultOfFunctionFibOnDisk() {
        FibCalculator fibCalculator = new Fib();
        FibCalculator proxy = CacheProxy.create(fibCalculator, FibCalculator.class);
        proxy.fib(10);
        proxy.fib(9);
        List<String> lines = Files.readAllLines(path);
        assertThat(lines.get(2)).isEqualTo("[9]=34");
    }
}
