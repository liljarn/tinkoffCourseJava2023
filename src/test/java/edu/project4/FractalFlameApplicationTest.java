package edu.project4;

import java.nio.file.Files;
import java.nio.file.Path;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FractalFlameApplicationTest {
    private final Path path = Path.of("src/main/resources/project4/img.png");

    @BeforeEach
    @SneakyThrows
    public void deleteCreatedImage() {
        Files.deleteIfExists(path);
    }

    @Test
    @DisplayName("Generating fractal flame test")
    public void createFractal_shouldGenerateFractalFlameImage() {
        new FractalFlameApplication().createFractal();
        Assertions.assertThat(Files.exists(path)).isTrue();
    }
}
