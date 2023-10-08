package edu.hw1.task2;

public final class CountDigits {
    private static final int DENOMINATOR = 10;

    private CountDigits() {
    }

    public static int countDigits(int number) {
        int changableNumber = Math.abs(number);
        if (changableNumber != 0) {
            int digitsCount = 0;
            while (changableNumber > 0) {
                digitsCount++;
                changableNumber /= DENOMINATOR;
            }
            return digitsCount;
        }
        return 1;
    }
}
