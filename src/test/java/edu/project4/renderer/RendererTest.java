package edu.project4.renderer;

import edu.project4.model.FractalImage;
import edu.project4.model.Rect;
import edu.project4.transformation.DiskTransformation;
import edu.project4.transformation.ExponentialTransformation;
import edu.project4.transformation.FisheyeTransformation;
import edu.project4.transformation.HandkerchiefTransformation;
import edu.project4.transformation.HeartTransformation;
import edu.project4.transformation.LinearTransformation;
import edu.project4.transformation.SinusoidalTransformation;
import edu.project4.transformation.SphericalTransformation;
import edu.project4.transformation.SpiralTransformation;
import edu.project4.transformation.SwirlTransformation;
import edu.project4.transformation.TangentTransformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class RendererTest {
    @Test
    @DisplayName("SingleThreadRenderer test")
    public void singleThreadRenderer_shouldWorkCorrectly() {
        Assertions.assertDoesNotThrow(() ->
            new SingleThreadRenderer().render(
                FractalImage.create(400, 400),
                new Rect(-4, -3, 8, 6),
                List.of(
                    new LinearTransformation(),
                    new DiskTransformation(),
                    new ExponentialTransformation(),
                    new FisheyeTransformation(),
                    new HandkerchiefTransformation()
                ),
                1000, 10000, 2
            )
        );
    }

    @Test
    @DisplayName("ParralelThreadRenderer test")
    public void parallelThreadRender_shouldWorkCorrectly() {
        Assertions.assertDoesNotThrow(() ->
            new ParallelThreadRenderer().render(
                FractalImage.create(400, 400),
                new Rect(-4, -3, 8, 6),
                List.of(
                    new HeartTransformation(),
                    new SinusoidalTransformation(),
                    new SwirlTransformation(),
                    new TangentTransformation(),
                    new SphericalTransformation(),
                    new SpiralTransformation()
                ),
                1000, 10000, 2
            )
        );
    }
}
