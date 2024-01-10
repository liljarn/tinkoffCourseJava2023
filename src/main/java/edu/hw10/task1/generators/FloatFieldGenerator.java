package edu.hw10.task1.generators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import java.lang.annotation.Annotation;
import java.util.Random;

public class FloatFieldGenerator implements FieldGenerator {
    @Override
    public Object generate(Annotation[] annotations) {
        float min = Float.MIN_VALUE;
        float max = Float.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                min = (float) ((Min) annotation).value();
            }
            if (annotation instanceof Max) {
                max = (float) ((Max) annotation).value();
            }
        }
        return new Random().nextFloat(min, max);
    }
}
