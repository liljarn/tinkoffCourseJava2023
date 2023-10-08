package edu.hw1.task8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw1.task8.KnightsOnTheBoard.knightBoardCapture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KnightsOnTheBoardTest {
    @Test
    @DisplayName("Кони не бьют друг друга")
    public void knightBoardCapture_shouldReturnTrue_whenKnightsDontBeatEachOther() {
        int[][] board = {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };
        assertThat(knightBoardCapture(board)).isTrue();
    }

    @Test
    @DisplayName("Кони могут бить друг друга")
    public void knightBoardCapture_shouldReturnTrue_whenKnightsCanBeatEachOther() {
        int[][] board = {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        };
        assertThat(knightBoardCapture(board)).isFalse();
    }

    @Test
    @DisplayName("Ввод пустого массива")
    public void knightBoardCapture_shouldThrowException_whenNullInput() {
        int[][] board = null;
        assertThatThrownBy(()->knightBoardCapture(board)).isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Длина доски не равна 8")
    public void knightBoardCapture_shouldThrowException_whenBoardLengthNotEqualEight() {
        int[][] board = {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1}
        };
        assertThatThrownBy(()->knightBoardCapture(board)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Ширина доски не равна 8")
    public void knightBoardCapture_shouldThrowException_whenBoardWidthNotEqualEight() {
        int[][] board = {
            {1, 0, 1, 0, 1, 0, 1},
            {0, 1, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 1, 0, 1},
            {0, 0, 1, 0, 0, 1, 0},
            {1, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 1, 0},
            {1, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 1, 0, 1, 0}
        };
        assertThatThrownBy(()->knightBoardCapture(board)).isInstanceOf(IllegalArgumentException.class);
    }
}
