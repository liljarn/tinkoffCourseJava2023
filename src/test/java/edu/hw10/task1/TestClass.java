package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;

public record TestClass(@Max(10) @Min(10) int value, @Max(5) @Min(5) double doubleValue) {
    public static TestClass create(@Max(10) @Min(10) int value, @Max(5) @Min(5) double doubleValue) {
        return new TestClass(value, doubleValue);
    }
}
