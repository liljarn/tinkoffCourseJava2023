package edu.hw3.task8;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {
    private final List<T> collection;
    private int currentIndex;

    public BackwardIterator(List<T> collection) {
        this.collection = collection;
        this.currentIndex = collection.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return currentIndex >= 0;
    }

    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return collection.get(currentIndex--);
    }

}
