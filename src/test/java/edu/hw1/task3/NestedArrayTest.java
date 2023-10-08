package edu.hw1.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw1.task3.NestedArray.isNested;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NestedArrayTest {
    @Test
    public void isNested_shouldReturnTrue_whenArrayCanBeNested() {
        int[] nestedArray = {1, 2, 3, 4};
        int[] originArray = {0, 6};
        assertThat(isNested(nestedArray, originArray)).isEqualTo(true);
    }

    @Test
    public void isNested_shouldReturnFalse_whenArrayCantBeNested() {
        int[] nestedArray = {9, 9, 8};
        int[] originArray = {8, 9};
        assertThat(isNested(nestedArray, originArray)).isEqualTo(false);
    }

    @Test
    @DisplayName("Ввод пустого массива")
    public void isNested_shouldReturnException_whenNoInput() {
        int[] nestedArray = null;
        int[] originArray = {};
        assertThatThrownBy(()->isNested(nestedArray, originArray)).isInstanceOf(NullPointerException.class);
    }
}
