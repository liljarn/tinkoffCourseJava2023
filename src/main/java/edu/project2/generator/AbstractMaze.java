package edu.project2.generator;

import edu.project2.model.Cell;

public abstract class AbstractMaze implements Generator {
    protected Cell[][] grid;
    protected int height;
    protected int width;

    protected AbstractMaze(int height, int width) {
        this.height = height;
        this.width = width;
        grid = new Cell[height][width];
    }

    protected boolean inBounds(int xCoordinate, int yCoordinate) {
        return xCoordinate >= 0 && xCoordinate < width && yCoordinate >= 0 && yCoordinate < height;
    }
}
