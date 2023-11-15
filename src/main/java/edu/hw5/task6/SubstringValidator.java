package edu.hw5.task6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SubstringValidator {
    private SubstringValidator() {
    }

    public static boolean isSubstring(String text, String substring) {
        if (text == null || substring == null) {
            throw new IllegalArgumentException("String can't be null");
        }
        Pattern substringPattern = Pattern.compile(substring);
        Matcher substringMatcher = substringPattern.matcher(text);
        return substringMatcher.find();
    }
}
