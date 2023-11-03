package edu.project2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

public record Maze(int height, int width, Cell[][] grid) {
}
