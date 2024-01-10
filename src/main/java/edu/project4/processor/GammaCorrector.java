package edu.project4.processor;

import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;

public class GammaCorrector implements ImageProcessor {
    private static final double GAMMA = 0.7;

    @Override
    public void process(FractalImage image) {
        double max = 0;
        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                Pixel pixel = image.getPixel(x, y);
                int hitCounter = pixel.getHitCounter();
                if (hitCounter != 0) {
                    pixel.setNormal(Math.log10(hitCounter));
                    if (pixel.getNormal() > max) {
                        max = pixel.getNormal();
                    }
                }
            }
        }
        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                Pixel pixel = image.getPixel(x, y);
                double normal = pixel.getNormal() / max;
                int r = pixel.getR();
                int g = pixel.getG();
                int b = pixel.getB();

                pixel.setR((int) (r * Math.pow(normal, 1.0 / GAMMA)));
                pixel.setG((int) (g * Math.pow(normal, 1.0 / GAMMA)));
                pixel.setB((int) (b * Math.pow(normal, 1.0 / GAMMA)));
            }
        }
    }
}
