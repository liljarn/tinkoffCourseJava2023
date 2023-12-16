package edu.hw10.task1.generators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import java.lang.annotation.Annotation;
import java.util.Random;

public class ByteFieldGenerator implements FieldGenerator {
    @Override
    public Object generate(Annotation[] annotations) {
        byte min = Byte.MIN_VALUE;
        byte max = Byte.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                min = (byte) ((Min) annotation).value();
            }
            if (annotation instanceof Max) {
                max = (byte) ((Max) annotation).value();
            }
        }
        return new Random().nextInt(min, max);
    }
}
