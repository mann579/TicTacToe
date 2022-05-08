package com.tictactoe.tictactoe.service;

import com.tictactoe.tictactoe.exception.InvalidException;
import com.tictactoe.tictactoe.exception.InvalidGame;
import com.tictactoe.tictactoe.exception.NotFound;
import com.tictactoe.tictactoe.model.Game;
import com.tictactoe.tictactoe.model.Play;
import com.tictactoe.tictactoe.model.Player;
import com.tictactoe.tictactoe.model.TicTac;
import com.tictactoe.tictactoe.persistance.GameRepository;
import com.tictactoe.tictactoe.utils.Winner;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.tictactoe.tictactoe.model.GameStatus.*;

@Service
@AllArgsConstructor
public class GameService {

    public Game createGame(Player player){
        Game game = new Game();
        game.setBoard(new int[10][10]);
        game.setGameId(UUID.randomUUID().toString());
        game.setFirstPlayer(player);
        game.setStatus(NEW);
        GameRepository.getInstance().setGames(game);
        return game;
    }

    public Game connectToGame(Player secondPlayer, String gameId) throws InvalidException, InvalidGame {
        if(!GameRepository.getInstance().getGames().containsKey(gameId)){
            throw new InvalidException("Game id doesn't exist");
        }
        Game game = GameRepository.getInstance().getGames().get(gameId);

        if(game.getSecondPlayer() != null){
            throw new InvalidGame("Game is not valid");
        }

        game.setSecondPlayer(secondPlayer);
        game.setStatus(IN_PROGRESS);
        GameRepository.getInstance().setGames(game);
        return game;
    }

    public Game connectToRandomGame(Player secondPlayer) throws NotFound {
        Game game = GameRepository.getInstance().getGames().values().stream()
                .filter(it->it.getStatus().equals(NEW))
                .findFirst().orElseThrow(()-> new NotFound("Game not found"));
        game.setSecondPlayer(secondPlayer);
        game.setStatus(IN_PROGRESS);
        GameRepository.getInstance().setGames(game);
        return game;
    }

    public Game play(Play play) throws NotFound, InvalidException {
        if(!GameRepository.getInstance().getGames().containsKey(play.getGameId())){
            throw new NotFound("Game not found");
        }

        Game game = GameRepository.getInstance().getGames().get(play.getGameId());
        if(game.getStatus().equals(FINISHED)){
            throw new InvalidException("Game is already completed");
        }

        int [][] board = game.getBoard();
        board[play.getCoordinateX()][play.getCoordinateY()] = play.getType().getValue();
        int winner = Winner.findWinner(game.getBoard());
        if (winner == 1) {
            game.setWinner(TicTac.X);
            game.setStatus(FINISHED);
        } else if (winner == 2) {
            game.setWinner(TicTac.O);
            game.setStatus(FINISHED);
        }
        GameRepository.getInstance().setGames(game);
        return game;
    }
}
