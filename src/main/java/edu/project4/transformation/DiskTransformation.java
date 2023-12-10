package edu.project4.transformation;

import edu.project4.model.Point;

public class DiskTransformation extends AbstractTransformation {
    @Override
    public Point apply(Point point) {
        double r = getRadius(point);
        double teta = getAngle(point);
        return new Point(teta / Math.PI * Math.sin(Math.PI * r), teta / Math.PI * Math.cos(Math.PI * r));
    }
}
