package edu.project4.renderer;

import edu.project4.model.AffineCoefficients;
import edu.project4.model.FractalImage;
import edu.project4.model.Rect;
import edu.project4.transformation.Transformation;
import java.util.List;

public class SingleThreadRenderer extends AbstractRenderer {
    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        int symmetry
    ) {
        AffineCoefficients[] affineCoefficients = getRandomAffineCoefficients(samples);
        for (int num = 0; num < samples; num++) {
            renderOneSample(canvas, world, variations, iterPerSample, symmetry, affineCoefficients);
        }
        return canvas;
    }
}
