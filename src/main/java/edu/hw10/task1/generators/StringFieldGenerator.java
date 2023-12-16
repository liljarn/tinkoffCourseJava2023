package edu.hw10.task1.generators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import java.lang.annotation.Annotation;
import java.util.Random;

public class StringFieldGenerator implements FieldGenerator {
    private static final int MIN_LENGTH = 5;
    private static final int MAX_LENGTH = 10;
    private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    @Override
    public Object generate(Annotation[] annotations) {
        long min = MIN_LENGTH;
        long max = MAX_LENGTH;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                min = ((Min) annotation).value();
            }
            if (annotation instanceof Max) {
                max = ((Max) annotation).value();
            }
        }
        Random random = new Random();
        long size = random.nextLong(min, max);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(ALPHABET[random.nextInt(ALPHABET.length)]);
        }
        return sb.toString();
    }
}
