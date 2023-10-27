package edu.project1.settings;

public enum WordLength {
    SHORT(4),
    MEDIUM(5),
    LONG(6);

    private final int length;

    WordLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
