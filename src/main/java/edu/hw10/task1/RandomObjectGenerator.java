package edu.hw10.task1;

import edu.hw10.task1.generators.FieldGenerator;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import lombok.SneakyThrows;
import static edu.hw10.task1.ClassesMap.CLASSES_MAP;

public class RandomObjectGenerator {
    @SneakyThrows
    public <T> T nextObject(Class<T> tClass) {
        Constructor<?> constructor = findMaxArgsConstructor(tClass);
        Parameter[] parameters = constructor.getParameters();
        Object[] objectList = getObjects(parameters);
        return tClass.cast(constructor.newInstance(objectList));
    }

    @SneakyThrows
    public <T> T nextObject(Class<T> tClass, String methodName) {
        Method method = null;
        for (Method classMethod : tClass.getMethods()) {
            if (classMethod.getName().equals(methodName)) {
                method = classMethod;
                break;
            }
        }
        if (method == null) {
            throw new IllegalArgumentException("Class without create method");
        }
        Parameter[] parameters = method.getParameters();
        Object[] objectList = getObjects(parameters);
        return tClass.cast(method.invoke(null, objectList));
    }

    private Constructor<?> findMaxArgsConstructor(Class<?> newClass) {
        Constructor<?> constructor = null;
        int parameterCount = -1;
        for (Constructor<?> construct : newClass.getConstructors()) {
            if (construct.getParameterCount() > parameterCount) {
                constructor = construct;
                parameterCount = constructor.getParameterCount();
            }
        }
        if (constructor == null) {
            throw new IllegalArgumentException("Class without not default constructor");
        }
        return constructor;
    }

    private Object[] getObjects(Parameter[] parameters) {
        Object[] objectList = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            FieldGenerator generator = CLASSES_MAP.get(parameters[i].getType());
            Annotation[] annotations = parameters[i].getAnnotations();
            objectList[i] = generator.generate(annotations);
        }
        return objectList;
    }
}
