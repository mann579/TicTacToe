package com.tictactoe.tictactoe.model;

import lombok.Data;

@Data
public class Play {

    private TicTac type;
    private int coordinateX;
    private int coordinateY;
    private String gameId;

}
