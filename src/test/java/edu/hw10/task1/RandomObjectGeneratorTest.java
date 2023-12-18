package edu.hw10.task1;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class RandomObjectGeneratorTest {
    @Test
    @DisplayName("Record generation with constructor method test")
    public void  nextObject_shouldReturnNewRecord_createdByConstructor() {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        var testClass = rog.nextObject(TestRecordClass.class);
        assertAll(
                () -> assertThat(testClass.value()).isEqualTo(10),
                () -> assertThat(testClass.doubleValue()).isCloseTo(5.0, Offset.offset(1.0))
        );
    }

    @Test
    @DisplayName("Record generation with factory method test")
    public void nextObject_shouldReturnNewRecord_createdByFactoryMethod() {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        var testClass = rog.nextObject(TestRecordClass.class, "create");
        assertAll(
            () -> assertThat(testClass.value()).isEqualTo(10),
            () -> assertThat(testClass.doubleValue()).isCloseTo(5.0, Offset.offset(1.0))
        );
    }

    @Test
    @DisplayName("POJO generation with constructor")
    public void nextObject_shouldReturnNewObject_createdByConstructor() {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        var testClass = rog.nextObject(TestClass.class);
        assertThat(testClass.getString()).isNotNull();
    }

    @Test
    @DisplayName("POJO generation with factory method test")
    public void nextObject_shouldReturnNewObject_createdByFactoryMethod() {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        var testClass = rog.nextObject(TestClass.class, "create");
        assertThat(testClass.getString()).isNotNull();
    }
}
