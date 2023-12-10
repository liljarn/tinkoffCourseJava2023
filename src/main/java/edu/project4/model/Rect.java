package edu.project4.model;

public record Rect(double x, double y, double width, double height) {
    public boolean contains(Point p) {
        return p.x() >= x && p.y() >= y && p.x() < width + p.x() && p.y() < height + y;
    }
}
