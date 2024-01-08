package edu.project4.transformation;

import edu.project4.model.Point;

public class FisheyeTransformation extends AbstractTransformation {
    @Override
    public Point apply(Point point) {
        double r = getRadius(point);
        return new Point(2 / (r + 1) * point.y(), 2 / (r + 1) * point.x());
    }
}
