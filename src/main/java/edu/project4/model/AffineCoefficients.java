package edu.project4.model;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public record AffineCoefficients(double a, double b, double c, double d, double e, double f, Color color) {
    private static final int COLOR_BOUND = 256;

    public static AffineCoefficients create() {
        while (true) {
            double aCoef = ThreadLocalRandom.current().nextDouble(-1, 1);
            double bCoef = ThreadLocalRandom.current().nextDouble(-1, 1);
            double cCoef = ThreadLocalRandom.current().nextDouble(-1, 1);
            double dCoef = ThreadLocalRandom.current().nextDouble(-1, 1);
            double eCoef = ThreadLocalRandom.current().nextDouble(-1, 1);
            double fCoef = ThreadLocalRandom.current().nextDouble(-1, 1);
            if (checkAffine(aCoef, bCoef, dCoef, eCoef)) {
                int r = ThreadLocalRandom.current().nextInt(COLOR_BOUND);
                int g = ThreadLocalRandom.current().nextInt(COLOR_BOUND);
                int b = ThreadLocalRandom.current().nextInt(COLOR_BOUND);
                Color color = new Color(r, g, b);
                return new AffineCoefficients(aCoef, bCoef, cCoef, dCoef, eCoef, fCoef, color);
            }
        }
    }

    private static boolean checkAffine(double a, double b, double d, double e) {
        return (Math.pow(a, 2) + Math.pow(d, 2) < 1
            && Math.pow(b, 2) + Math.pow(e, 2) < 1
            && Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(d, 2) + Math.pow(e, 2) < 1 + Math.pow((a * e - b * d), 2));
    }
}
