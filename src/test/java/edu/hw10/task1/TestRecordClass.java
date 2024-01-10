package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;

public record TestRecordClass(@Max(10) @Min(10) int value, @Max(5) @Min(5) double doubleValue, Byte aByte) {
    public static TestRecordClass create(@Max(10) @Min(10) int value, @Max(5) @Min(5) double doubleValue, Byte aByte) {
        return new TestRecordClass(value, doubleValue, aByte);
    }
}
