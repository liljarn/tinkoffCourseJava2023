package edu.project4.transformation;

import edu.project4.model.Point;

public class HandkerchiefTransformation extends AbstractTransformation {
    @Override
    public Point apply(Point point) {
        double r = getRadius(point);
        double teta = getAngle(point);
        return new Point(r * Math.sin(teta + r), r * Math.cos(teta - r));
    }
}
