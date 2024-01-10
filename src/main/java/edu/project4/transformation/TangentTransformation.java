package edu.project4.transformation;

import edu.project4.model.Point;

public class TangentTransformation extends AbstractTransformation {
    @Override
    public Point apply(Point point) {
        return new Point(Math.sin(point.x()) / Math.cos(point.y()), Math.tan(point.y()));
    }
}
