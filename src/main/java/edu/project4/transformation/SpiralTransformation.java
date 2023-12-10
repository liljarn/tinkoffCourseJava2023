package edu.project4.transformation;

import edu.project4.model.Point;

public class SpiralTransformation extends AbstractTransformation {
    @Override
    public Point apply(Point point) {
        double r = 1.0 / getRadius(point);
        double teta = getAngle(point);
        return new Point(r * (Math.cos(teta) + Math.sin(r)), r * (Math.sin(teta) - Math.cos(r)));
    }
}
