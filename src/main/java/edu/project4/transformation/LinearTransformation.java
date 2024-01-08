package edu.project4.transformation;

import edu.project4.model.Point;

public class LinearTransformation extends AbstractTransformation {
    @Override
    public Point apply(Point point) {
        return new Point(point.x(), point.y());
    }
}
