package edu.hw1.task4;

public final class BrokenString {
    private BrokenString() {
    }

    public static String fixString(String brokenString) {
        if (brokenString == null) {
            throw new NullPointerException("Null string");
        }
        if (brokenString.isEmpty()) {
            throw new IllegalArgumentException("Empty string");
        }
        char[] fixedString = brokenString.toCharArray();
        for (int i = 0; i < fixedString.length - 1; i += 2) {
            char temp = fixedString[i + 1];
            fixedString[i + 1] = fixedString[i];
            fixedString[i] = temp;
        }
        return new String(fixedString);
    }
}
