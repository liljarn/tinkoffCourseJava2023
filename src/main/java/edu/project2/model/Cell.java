package edu.project2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class Cell {
    private final int row;
    private final int column;
    @Setter
    private Type type;

    public enum Type { WALL, PASSAGE }
}
