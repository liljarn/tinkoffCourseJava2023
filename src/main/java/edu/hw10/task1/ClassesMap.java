package edu.hw10.task1;

import edu.hw10.task1.generators.BooleanFieldGenerator;
import edu.hw10.task1.generators.ByteFieldGenerator;
import edu.hw10.task1.generators.DoubleFieldGenerator;
import edu.hw10.task1.generators.FieldGenerator;
import edu.hw10.task1.generators.FloatFieldGenerator;
import edu.hw10.task1.generators.IntegerFieldGenerator;
import edu.hw10.task1.generators.LongFieldGenerator;
import edu.hw10.task1.generators.ShortFieldGenerator;
import edu.hw10.task1.generators.StringFieldGenerator;
import java.util.HashMap;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ClassesMap {
    public static final Map<Class<?>, FieldGenerator> CLASSES_MAP = new HashMap<>();

    static {
        CLASSES_MAP.put(Boolean.class, new BooleanFieldGenerator());
        CLASSES_MAP.put(Byte.class, new ByteFieldGenerator());
        CLASSES_MAP.put(Double.class, new DoubleFieldGenerator());
        CLASSES_MAP.put(Float.class, new FloatFieldGenerator());
        CLASSES_MAP.put(Integer.class, new IntegerFieldGenerator());
        CLASSES_MAP.put(Long.class, new LongFieldGenerator());
        CLASSES_MAP.put(Short.class, new ShortFieldGenerator());
        CLASSES_MAP.put(String.class, new StringFieldGenerator());
        CLASSES_MAP.put(boolean.class, new BooleanFieldGenerator());
        CLASSES_MAP.put(byte.class, new ByteFieldGenerator());
        CLASSES_MAP.put(double.class, new DoubleFieldGenerator());
        CLASSES_MAP.put(float.class, new FloatFieldGenerator());
        CLASSES_MAP.put(int.class, new IntegerFieldGenerator());
        CLASSES_MAP.put(long.class, new LongFieldGenerator());
        CLASSES_MAP.put(short.class, new ShortFieldGenerator());
    }
}
