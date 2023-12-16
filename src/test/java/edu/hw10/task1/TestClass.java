package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import lombok.Getter;

@Getter
public class TestClass {
   private final String string;
   private final Boolean bool;
   private final Float floatVal;
   private final Short shortVal;

    public TestClass(@NotNull String string, Boolean bool, Float floatVal, Short shortVal) {
        this.string = string;
        this.bool = bool;
        this.floatVal = floatVal;
        this.shortVal = shortVal;
    }

    public static TestClass create(
        @NotNull String string,
        Boolean bool,
        Float floatVal,
        Short shortVal
    ) {
        return new TestClass(string, bool, floatVal, shortVal);
    }
}
