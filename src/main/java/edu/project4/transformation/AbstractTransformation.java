package edu.project4.transformation;

import edu.project4.model.Point;

public abstract class AbstractTransformation implements Transformation {
    protected double getRadius(Point pw) {
        return Math.sqrt(Math.pow(pw.x(), 2) + Math.pow(pw.y(), 2));
    }

    protected double getDoubleRadius(Point pw) {
        return Math.pow(pw.x(), 2) + Math.pow(pw.y(), 2);
    }

    protected double getAngle(Point pw) {
        return Math.atan(pw.x() / pw.y());
    }
}
