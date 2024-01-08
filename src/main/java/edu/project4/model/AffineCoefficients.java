package edu.project4.model;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public record AffineCoefficients(double a, double b, double c, double d, double e, double f, Color color) {
    private static final int COLOR_BOUND = 256;

    public static AffineCoefficients create() {
        while (true) {
            ThreadLocalRandom rand = ThreadLocalRandom.current();
            double aCoef = rand.nextDouble(-1, 1);
            double bCoef = rand.nextDouble(-1, 1);
            double cCoef = rand.nextDouble(-1, 1);
            double dCoef = rand.nextDouble(-1, 1);
            double eCoef = rand.nextDouble(-1, 1);
            double fCoef = rand.nextDouble(-1, 1);
            if (checkAffine(aCoef, bCoef, dCoef, eCoef)) {
                int r = rand.nextInt(COLOR_BOUND);
                int g = rand.nextInt(COLOR_BOUND);
                int b = rand.nextInt(COLOR_BOUND);
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
