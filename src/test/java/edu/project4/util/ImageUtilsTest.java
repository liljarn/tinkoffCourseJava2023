package edu.project4.util;

import edu.project4.model.FractalImage;
import edu.project4.model.ImageFormat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;

public class ImageUtilsTest {
    @Test
    @DisplayName("Save picture test")
    public void save_shouldSaveImageToDisc(@TempDir Path path) {
        ImageUtils.save(FractalImage.create(400, 400), path.resolve("img.png"), ImageFormat.PNG);
        assertThat(path.resolve("img.png")).exists();
    }
}
