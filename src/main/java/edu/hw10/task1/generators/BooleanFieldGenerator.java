package edu.hw10.task1.generators;

import java.lang.annotation.Annotation;
import java.util.Random;

public class BooleanFieldGenerator implements FieldGenerator {
    @Override
    public Object generate(Annotation[] annotations) {
        return new Random().nextBoolean();
    }
}
