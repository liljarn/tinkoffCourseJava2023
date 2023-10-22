package edu.hw3.task7;

import java.util.Comparator;

public class TreeMapComparator<T extends Comparable<T>> implements Comparator<T> {
    @Override
    public int compare(T key1, T key2) {
        if (key1 == key2) {
            return 0;
        }
        if (key1 == null || key2 == null) {
            return -1;
        }
        return key1.compareTo(key2);
    }
}
