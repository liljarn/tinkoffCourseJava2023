package edu.hw5.task7;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ZeroOneRegex {
    private static final Pattern FIRST_PATTERN = Pattern.compile("^[01]{2}0[01]*$");
    private static final Pattern SECOND_PATTERN = Pattern.compile("^([01])[01]*\\1$");
    private static final Pattern THIRD_PATTERN = Pattern.compile("^[01]{1,3}$");
    private static final String NULL_MESSAGE = "String can't be null";

    private ZeroOneRegex() {
    }

    public static boolean isMatchesFirstPatten(String zerosAndOnes) {
        if (zerosAndOnes == null) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        Matcher firstMatcher = FIRST_PATTERN.matcher(zerosAndOnes);
        return firstMatcher.matches();
    }

    public static boolean isMatchesSecondPatten(String zerosAndOnes) {
        if (zerosAndOnes == null) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        Matcher secondMatcher = SECOND_PATTERN.matcher(zerosAndOnes);
        return secondMatcher.matches();
    }

    public static boolean isMatchesThirdPatten(String zerosAndOnes) {
        if (zerosAndOnes == null) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        Matcher thirdMatcher = THIRD_PATTERN.matcher(zerosAndOnes);
        return thirdMatcher.matches();
    }
}
