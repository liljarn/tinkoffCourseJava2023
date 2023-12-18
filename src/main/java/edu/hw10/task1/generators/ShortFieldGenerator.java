package edu.hw10.task1.generators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import java.lang.annotation.Annotation;
import java.util.Random;

public class ShortFieldGenerator implements FieldGenerator {
    @Override
    public Object generate(Annotation[] annotations) {
        short min = Short.MIN_VALUE;
        short max = Short.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                min = (short) ((Min) annotation).value();
            }
            if (annotation instanceof Max) {
                max = (short) ((Max) annotation).value();
            }
        }
        return (short) new Random().nextInt(min, max);
    }
}
