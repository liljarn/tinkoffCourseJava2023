package edu.hw1.task5;

public final class SpecialPalindrome {
    private static final int DIGIT = 10;

    private SpecialPalindrome() {
    }

    public static boolean isPalindrome(String palindrome) {
        for (int i = 0; i < palindrome.length() / 2; i++) {
            if (palindrome.charAt(i) != palindrome.charAt(palindrome.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindromeDescendant(int number) {
        String stringNumber = Integer.toString(number);
        if (stringNumber.length() < 2 || number < 0) {
            return false;
        }
        if (isPalindrome(stringNumber)) {
            return true;
        }
        int descendantNumber = 0;
        for (int i = 0; i < stringNumber.length() - 1; i += 2) {
            descendantNumber = (int) (descendantNumber * DIGIT
                + Character.getNumericValue(stringNumber.charAt(i))
                + Character.getNumericValue(stringNumber.charAt(i + 1)));
        }
        if (stringNumber.length() % 2 != 0) {
            descendantNumber =
                descendantNumber * DIGIT + Character.getNumericValue(stringNumber.charAt(stringNumber.length() - 1));
        }
        return isPalindromeDescendant(descendantNumber);
    }
}
