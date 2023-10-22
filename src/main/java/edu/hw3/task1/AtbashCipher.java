package edu.hw3.task1;

public final class AtbashCipher {
    private static final int ASCII_A = 65;
    private static final int ASCII_Z = 90;

    private AtbashCipher() {
    }

    private static char convertCharacterToAtbashCipher(int alphabeticCharacter) {
        return (char) (ASCII_Z - alphabeticCharacter + ASCII_A);
    }

    public static String atbash(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Null or empty string");
        }
        StringBuilder atbashString = new StringBuilder();
        boolean isLowerCase;
        for (int i = 0; i < str.length(); i++) {
            char curChar = str.charAt(i);
            if (Character.isAlphabetic(curChar)) {
                if (Character.isLowerCase(curChar)) {
                    curChar = Character.toUpperCase(curChar);
                    isLowerCase = true;
                } else {
                    isLowerCase = false;
                }
                curChar = convertCharacterToAtbashCipher(curChar);
                if (isLowerCase) {
                    curChar = Character.toLowerCase(curChar);
                }
            }
            atbashString.append(curChar);
        }
        return atbashString.toString();
    }
}
