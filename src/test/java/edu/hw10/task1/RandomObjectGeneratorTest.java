package edu.hw10.task1;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class RandomObjectGeneratorTest {
    @Test
    public void aboba() {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        var testClass = rog.nextObject(TestClass.class);
        assertAll(
                () -> assertThat(testClass.value()).isEqualTo(10),
                () -> assertThat(testClass.doubleValue()).isCloseTo(5.0, Offset.offset(1.0))
        );
    }

    @Test
    public void aboba2() {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        var testClass = rog.nextObject(TestClass.class, "create");
        assertAll(
            () -> assertThat(testClass.value()).isEqualTo(10),
            () -> assertThat(testClass.doubleValue()).isCloseTo(5.0, Offset.offset(1.0))
        );
    }
}
