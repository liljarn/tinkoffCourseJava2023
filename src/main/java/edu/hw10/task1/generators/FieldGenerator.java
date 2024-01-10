package edu.hw10.task1.generators;

import java.lang.annotation.Annotation;

public interface FieldGenerator {
    Object generate(Annotation[] annotations);
}
