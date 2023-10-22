package edu.hw3.task4;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class RomanNumbers {
    private static final int MINIMAL_VALUE = 1;
    private static final int MAXIMAL_VALUE = 3999;
    private static final int[] VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] ROMAN_VALUES =
        {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static final Map<Integer, String> ROMAN_DICTIONARY = new LinkedHashMap<>() {{
        for (int i = 0; i < VALUES.length; i++) {
            put(VALUES[i], ROMAN_VALUES[i]);
        }
    }};

    private RomanNumbers() {
    }

    public static String convertToRoman(int arabicNumber) {
        if (arabicNumber < MINIMAL_VALUE || arabicNumber >= MAXIMAL_VALUE) {
            throw new IllegalArgumentException("Can't be casted to roman number");
        }
        StringBuilder romanNumber = new StringBuilder();
        int arabicCopy = arabicNumber;
        Set<Integer> keys = ROMAN_DICTIONARY.keySet();
        for (int key: keys) {
            while (arabicCopy >= key) {
                romanNumber.append(ROMAN_DICTIONARY.get(key));
                arabicCopy -= key;
            }
        }
        return romanNumber.toString();
    }
}
