package edu.hw1.task7;

public final class CyclicBitShift {
    private CyclicBitShift() {
    }

    public static int rotateRight(int number, int shift) {
        if (number <= 0 || shift < 0) {
            return -1;
        }
        String binaryNumber = Integer.toBinaryString(number);
        int shiftedNumber = 0;
        int newPos;
        int module =  binaryNumber.length();
        for (int i = 0; i < module; i++) {
            if (binaryNumber.charAt(i) == '1') {
                newPos = (shift + i) % module;
                shiftedNumber += (int) Math.pow(2, module - 1 - newPos);
            }
        }
        return shiftedNumber;
    }

    public static int rotateLeft(int number, int shift) {
        if (number <= 0 || shift < 0) {
            return -1;
        }
        String binaryNumber = Integer.toBinaryString(number);
        int shiftedNumber = 0;
        int newPos;
        int module =  binaryNumber.length();
        for (int i = 0; i < module; i++) {
            if (binaryNumber.charAt(i) == '1') {
                newPos = (i - shift) % module;
                if (newPos < 0) {
                    newPos += module;
                }
                shiftedNumber += (int) Math.pow(2, module - 1 - newPos);
            }
        }
        return shiftedNumber;
    }
}
