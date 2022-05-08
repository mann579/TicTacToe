package com.tictactoe.tictactoe.model;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TicTac {
    X( 1), O(2);

    private int value;
}
