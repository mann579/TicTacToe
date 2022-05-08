package com.tictactoe.tictactoe.model;

import lombok.Data;

@Data
public class Game {

    private String gameId;
    private Player firstPlayer;
    private Player secondPlayer;
    private GameStatus status;
    private int [][] board;
    private TicTac winner;

}
