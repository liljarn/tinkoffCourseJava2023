package edu.project4.renderer;

import edu.project4.model.AffineCoefficients;
import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;
import edu.project4.model.Point;
import edu.project4.model.Rect;
import edu.project4.transformation.Transformation;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractRenderer implements Renderer {
    protected static final int INIT_STEPS = -20;

    protected AffineCoefficients[] getRandomAffineCoefficients(int samples) {
        AffineCoefficients[] coefficients = new AffineCoefficients[samples];
        for (int i = 0; i < samples; i++) {
            coefficients[i] = AffineCoefficients.create();
        }
        return coefficients;
    }

    protected Point transform(AffineCoefficients affine, Point pw) {
        return new Point(
            affine.a() * pw.x() + affine.b() * pw.y() + affine.c(),
            affine.d() * pw.x() + affine.e() * pw.y() + affine.f()
        );
    }

    protected Point rotate(Point pw, double theta) {
        double xNew = pw.x() * Math.cos(theta) - pw.y() * Math.sin(theta);
        double yNew = pw.x() * Math.sin(theta) + pw.y() * Math.cos(theta);
        return new Point(xNew, yNew);
    }

    protected void recolor(Pixel pixel, AffineCoefficients affine) {
        if (pixel.getHitCounter() == 0) {
            pixel.setR(affine.color().getRed());
            pixel.setG(affine.color().getGreen());
            pixel.setB(affine.color().getBlue());
        } else {
            pixel.setR((pixel.getR() + affine.color().getRed()) / 2);
            pixel.setG((pixel.getG() + affine.color().getGreen()) / 2);
            pixel.setB((pixel.getB() + affine.color().getBlue()) / 2);
        }
    }

    protected void renderOneSample(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int iterPerSample,
        int symmetry,
        AffineCoefficients[] affineCoefficients
    ) {
        Point pw =
            new Point(
                ThreadLocalRandom.current().nextDouble(world.width()),
                ThreadLocalRandom.current().nextDouble(world.height())
            );
        for (int step = INIT_STEPS; step < iterPerSample; step++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(affineCoefficients.length);
            AffineCoefficients randomAffine = affineCoefficients[randomIndex];
            pw = transform(randomAffine, pw);
            Transformation transformation = variations.get(ThreadLocalRandom.current().nextInt(variations.size()));
            pw = transformation.apply(pw);
            if (step >= 0) {
                double theta = 0.0;
                for (int s = 0; s < symmetry; s++) {
                    theta += 2 * Math.PI / symmetry;
                    Point point = rotate(pw, theta);
                    if (!world.contains(point)) {
                        continue;
                    }
                    Pixel pixel = canvas.getPixel(
                        (int) ((point.x() - world.x()) / (world.width()) * canvas.width()),
                        (int) ((point.y() - world.y()) / (world.height()) * canvas.height())
                    );
                    if (pixel == null) {
                        continue;
                    }
                    synchronized (pixel) {
                        recolor(pixel, randomAffine);
                        pixel.setHitCounter(pixel.getHitCounter() + 1);
                    }
                }
            }
        }
    }
}
