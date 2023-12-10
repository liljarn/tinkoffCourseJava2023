package edu.project4.transformation;

import edu.project4.model.Point;

public class ExponentialTransformation extends AbstractTransformation {
    @Override
    public Point apply(Point point) {
        double coef = Math.exp(point.x() - 1);
        return new Point(coef * Math.cos(Math.PI * point.y()), coef * Math.sin(Math.PI * point.y()));
    }
}
