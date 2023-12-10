package edu.project4.util;

import edu.project4.model.FractalImage;
import edu.project4.model.ImageFormat;
import edu.project4.model.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class ImageUtils {
    @SneakyThrows
    public static void save(FractalImage image, Path filename, ImageFormat format) {
        BufferedImage picture = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                Pixel pixel = image.data()[y][x];
                Color color = new Color(pixel.getR(), pixel.getG(), pixel.getB());
                picture.setRGB(x, y, color.getRGB());
            }
        }
        ImageIO.write(picture, format.name(), filename.toFile());
    }
}
