package com.tictactoe.tictactoe.controller.dto;

import com.tictactoe.tictactoe.model.Player;
import lombok.Data;

@Data
public class Connect {
    private Player player;
    private String gameId;
}
