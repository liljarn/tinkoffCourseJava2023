package edu.project4.transformation;

import edu.project4.model.Point;

public class SphericalTransformation extends AbstractTransformation {
    @Override
    public Point apply(Point point) {
        double coef = 1.0 / Math.pow(getRadius(point), 2);
        return new Point(coef * point.x(), coef * point.y());
    }
}
