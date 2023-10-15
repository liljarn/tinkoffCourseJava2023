package edu.hw1.task8;

public final class KnightsOnTheBoard {
    private static final String ERROR_MESSAGE = "Bad data";

    private static final int BOARD_SIDE = 8;

    private final static int[] MOVES_X = {-2, -2, -1, 1, 2, 2, 1, -1};
    private final static int[] MOVES_Y = {-1, 1, 2, 2, 1, -1, -2, -2};

    private KnightsOnTheBoard() {
    }

    public static boolean knightBoardCapture(int[][] board) {
        if (board == null) {
            throw new NullPointerException("Empty array");
        }
        if (board.length != BOARD_SIDE) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        for (int i = 0; i < BOARD_SIDE; i++) {
            if (board[i].length != BOARD_SIDE) {
                throw new IllegalArgumentException(ERROR_MESSAGE);
            }
        }
        for (int i = 0; i < BOARD_SIDE; i++) {
            for (int j = 0; j < BOARD_SIDE; j++) {
                if (board[i][j] == 1) {
                    for (int k = 0; k < BOARD_SIDE; k++) {
                        int x = i + MOVES_X[k];
                        int y = j + MOVES_Y[k];
                        if (x >= 0 && x < BOARD_SIDE && y >= 0 && y < BOARD_SIDE && board[x][y] == 1) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
