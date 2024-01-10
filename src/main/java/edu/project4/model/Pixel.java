package edu.project4.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pixel {
    private int r;
    private int b;
    private int g;
    private int hitCounter;
    private double normal;
}
