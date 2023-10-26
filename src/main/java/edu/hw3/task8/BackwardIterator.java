package edu.hw3.task8;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {
    private final T[] elements;
    private int currentIndex;

    public BackwardIterator(Collection<T> collection) {
        this.elements = (T[]) collection.toArray();
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
        return elements[currentIndex--];
    }

}
