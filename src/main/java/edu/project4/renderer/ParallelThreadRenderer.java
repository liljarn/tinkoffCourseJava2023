package edu.project4.renderer;

import edu.project4.model.AffineCoefficients;
import edu.project4.model.FractalImage;
import edu.project4.model.Rect;
import edu.project4.transformation.Transformation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;

public class ParallelThreadRenderer extends AbstractRenderer {
    private final static int AWAIT_TIME = 60;
    private final ExecutorService executorService = Executors.newFixedThreadPool(6);

    @Override
    @SneakyThrows
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        int symmetry
    ) {
        AffineCoefficients[] affineCoefficients = getRandomAffineCoefficients(samples);
        for (int i = 0; i < samples; i++) {
            executorService.execute(() ->
                renderOneSample(canvas, world, variations, iterPerSample, symmetry, affineCoefficients)
            );
        }
        executorService.shutdown();
        executorService.awaitTermination(AWAIT_TIME, TimeUnit.SECONDS);
        return canvas;
    }
}
