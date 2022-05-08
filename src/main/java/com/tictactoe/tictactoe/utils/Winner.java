package com.tictactoe.tictactoe.utils;

import org.springframework.stereotype.Component;

@Component
public class Winner {
    public static int findWinner(int[][] board) {
        int column = isAnyColumnClosed(board);
        int row = isAnyRowClosed(board);
        int diagonalA = isDiagonalAClosed(board);
        int diagonalB = isDiagonalBClosed(board);
        if (column != 0) {
            return column;
        } else if (row != 0) {
            return row;
        } else if (diagonalA != 0) {
            return diagonalA;
        } else if (diagonalB != 0) {
            return diagonalB;
        }
        return 0;
    }

    private static int isAnyRowClosed(int[][] board) {
        int maxIteration = board.length - 1;
        for (int i = 0; i <= maxIteration; i++) {
            int baseValue = board[i][0];
            int count = 0;
            for (int j = 0; j <= maxIteration; j++) {
                int checkedValue = board[i][j];
                if (checkedValue != 0  && checkedValue == baseValue) {
                    count++;
                    if (count == 5) {
                        return baseValue;
                    }
                } else if (checkedValue != 0 && checkedValue != baseValue) {
                    count = 0;
                    baseValue = checkedValue;
                }
            }
        }
        return 0;
    }

    private static int isAnyColumnClosed(int[][] board) {
        int maxIteration = board.length - 1;
        for (int i = 0; i <= maxIteration; i++) {
            int baseValue = board[0][i];
            int count = 0;
            for (int j = 0; j <= maxIteration; j++) {
                int checkedValue = board[j][i];
                if (checkedValue != 0  && checkedValue == baseValue) {
                    count++;
                    if (count == 5) {
                        return baseValue;
                    }
                } else if (checkedValue != 0 && checkedValue != baseValue) {
                    count = 0;
                    baseValue = checkedValue;
                }
            }
        }
        return 0;
    }

    private static int isDiagonalAClosed(int[][] board) {
        int maxIteration = board.length - 1;
        int baseValue = board[0][0];
        int count = 0;
        for (int i = 0; i <= maxIteration; i++) {
            int checkedValue = board[i][i];
            if (checkedValue != 0  && checkedValue == baseValue) {
                count++;
                if (count == 5) {
                    return baseValue;
                }
            } else if (checkedValue != 0) {
                count = 0;
                baseValue = checkedValue;
            }
        }
        return 0;
    }

    private static int isDiagonalBClosed(int[][] board) {
        int maxIteration = board.length - 1;
        int baseValue = board[0][maxIteration];
        for (int i = 0; i <= maxIteration; i++) {
            int checkedValue = board[i][maxIteration - i];
            int count = 0;
            if (checkedValue != 0  && checkedValue == baseValue) {
                count++;
                if (count == 5) {
                    return baseValue;
                }
            } else if (checkedValue != 0) {
                count = 0;
                baseValue = checkedValue;
            }
        }
        return 0;
    }
}
