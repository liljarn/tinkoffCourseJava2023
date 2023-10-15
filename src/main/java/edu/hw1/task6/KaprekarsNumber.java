package edu.hw1.task6;

import java.util.Arrays;

public final class KaprekarsNumber {
    private static final int KAPREKARS_NUMBER = 6174;
    private static final int LEAST = 1000;
    private static final int MOST = 9999;

    private KaprekarsNumber() {
    }

    public static void sortInDescendingOrder(char[] charsArray) {
        for (int i = 0; i < charsArray.length / 2; i++) {
            char temp = charsArray[charsArray.length - 1 - i];
            charsArray[charsArray.length - 1 - i] = charsArray[i];
            charsArray[i] = temp;
        }
    }

    public static int countK(int number) {
        if (number < LEAST || number > MOST) {
            throw new IllegalArgumentException("Out of bounds");
        }
        if (number == KAPREKARS_NUMBER) {
            return 0;
        }
        String stringNumber = Integer.toString(number);
        char[] descendingOrderNumber = stringNumber.toCharArray();
        char[] ascendingOrderNumber = stringNumber.toCharArray();
        Arrays.sort(ascendingOrderNumber);
        Arrays.sort(descendingOrderNumber);
        sortInDescendingOrder(descendingOrderNumber);
        int newNumber = Integer.parseInt(new String(descendingOrderNumber))
            - Integer.parseInt(new String(ascendingOrderNumber));
        if (newNumber == 0) {
            throw new IllegalArgumentException("Four same digits");
        }
        return countK(newNumber) + 1;
    }
}
