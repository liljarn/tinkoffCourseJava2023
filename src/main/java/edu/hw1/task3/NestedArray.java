package edu.hw1.task3;

import java.util.Arrays;

public final class NestedArray {
    private NestedArray() {
    }

    public static boolean isNested(int[] nestedArray, int[] originArray) {
        if (nestedArray == null || originArray == null || nestedArray.length == 0 || originArray.length == 0) {
            throw new NullPointerException("Empty input");
        }
        int minInOriginArray = Arrays.stream(originArray).min().getAsInt();
        int maxInOriginArray = Arrays.stream(originArray).max().getAsInt();
        int minInNestedArray = Arrays.stream(nestedArray).min().getAsInt();
        int maxInNestedArray = Arrays.stream(nestedArray).max().getAsInt();
        return minInOriginArray < minInNestedArray && maxInOriginArray > maxInNestedArray;
    }
}
