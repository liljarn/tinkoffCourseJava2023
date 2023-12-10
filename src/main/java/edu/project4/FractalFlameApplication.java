package edu.project4;

import edu.project4.model.FractalImage;
import edu.project4.model.ImageFormat;
import edu.project4.model.Rect;
import edu.project4.processor.GammaCorrector;
import edu.project4.processor.ImageProcessor;
import edu.project4.renderer.ParallelThreadRenderer;
import edu.project4.renderer.Renderer;
import edu.project4.transformation.ExponentialTransformation;
import edu.project4.transformation.FisheyeTransformation;
import edu.project4.transformation.LinearTransformation;
import edu.project4.util.ImageUtils;
import java.nio.file.Path;
import java.util.List;

public class FractalFlameApplication {
    @SuppressWarnings("checkstyle:MagicNumber")
    public void createFractal() {
        Renderer renderer = new ParallelThreadRenderer();
        Rect rect = new Rect(-3, -2, 6, 4);
        FractalImage image = renderer.render(FractalImage.create(1920, 1080), rect,
            List.of(
                new ExponentialTransformation(), new FisheyeTransformation(), new LinearTransformation()
            ),
            3,
            10000,
            6
        );
        ImageProcessor processor = new GammaCorrector();
        processor.process(image);
        Path path = Path.of("img.png");
        ImageUtils.save(image, path, ImageFormat.PNG);
    }
}
