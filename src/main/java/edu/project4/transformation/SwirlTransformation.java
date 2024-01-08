package edu.project4.transformation;

import edu.project4.model.Point;

public class SwirlTransformation extends AbstractTransformation {
    @Override
    public Point apply(Point point) {
        double r = getDoubleRadius(point);
        return new Point(
            point.x() * Math.sin(r) - point.y() * Math.cos(r),
            point.x() * Math.cos(r) + point.y() * Math.sin(r)
        );
    }
}
