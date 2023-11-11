package edu.hw5.task8;

import java.util.regex.Pattern;

public final class BonusZeroOneRegex {
    private static final String NULL_MESSAGE = "String can't be null";

    private BonusZeroOneRegex() {
    }

    public static boolean isLengthOdd(String zerosAndOnes) {
        if (zerosAndOnes == null) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        Pattern pattern = Pattern.compile("^[01]([01][01])*$");
        return pattern.matcher(zerosAndOnes).matches();
    }

    private static boolean isStartedWithZeroAndOdd(String zerosAndOnes) {
        if (zerosAndOnes == null) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        Pattern pattern = Pattern.compile("^0([01][01])*$");
        return pattern.matcher(zerosAndOnes).matches();
    }

    private static boolean isStartedWithOneAndEven(String zerosAndOnes) {
        if (zerosAndOnes == null) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        Pattern pattern = Pattern.compile("^1[01]([01][01])*$");
        return pattern.matcher(zerosAndOnes).matches();
    }

    public static boolean isStartedWithZeroAndOddOrIsStartedWithOneAndEven(String zerosAndOnes) {
        return isStartedWithOneAndEven(zerosAndOnes) || isStartedWithZeroAndOdd(zerosAndOnes);
    }

    public static boolean isNumberOfZerosDivisibleByThree(String zerosAndOnes) {
        if (zerosAndOnes == null) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        Pattern pattern = Pattern.compile("^((1*01*01*01*)+)|(1+)$"); //0 кратен трём
        return pattern.matcher(zerosAndOnes).matches();
    }

    public static boolean isNotTwoOrThreeOnesInRow(String zerosAndOnes) {
        if (zerosAndOnes == null) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        Pattern pattern = Pattern.compile("^(?!11$|111$)[01]*");
        return pattern.matcher(zerosAndOnes).matches();
    }

    public static boolean isEveryOddNumberIsOne(String zerosAndOnes) {
        if (zerosAndOnes == null) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        Pattern pattern = Pattern.compile("^((10)+1?)|(1)$");
        return pattern.matcher(zerosAndOnes).matches();
    }

    public static boolean doesContainAtLeastTwoZerosAndLessThanTwoOnes(String zerosAndOnes) {
        if (zerosAndOnes == null) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        Pattern pattern = Pattern.compile("^(1?0{2,})|(0+1?0+)|(0{2,}1?)$");
        return pattern.matcher(zerosAndOnes).matches();
    }

    public static boolean hasNotTwoOnesInRow(String zerosAndOnes) {
        if (zerosAndOnes == null) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        Pattern pattern = Pattern.compile("^(0*1?0+1?0*)+$|^[01]$");
        return pattern.matcher(zerosAndOnes).matches();
    }
}
