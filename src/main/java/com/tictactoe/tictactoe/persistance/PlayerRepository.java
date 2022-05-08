package com.tictactoe.tictactoe.persistance;

import com.tictactoe.tictactoe.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> { }
