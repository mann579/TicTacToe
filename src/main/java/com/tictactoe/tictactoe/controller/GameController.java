package com.tictactoe.tictactoe.controller;

import com.tictactoe.tictactoe.controller.dto.Connect;
import com.tictactoe.tictactoe.exception.InvalidException;
import com.tictactoe.tictactoe.exception.InvalidGame;
import com.tictactoe.tictactoe.exception.NotFound;
import com.tictactoe.tictactoe.model.Game;
import com.tictactoe.tictactoe.model.Play;
import com.tictactoe.tictactoe.model.Player;
import com.tictactoe.tictactoe.persistance.PlayerRepository;
import com.tictactoe.tictactoe.service.GameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

//@controllerAdvice for handling the request

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    private PlayerRepository playerRepo;

    @PostMapping("/start")
    public ResponseEntity<Game> start(@RequestBody Player player){
        log.info("start game request: {}", player);
        return ResponseEntity.ok(gameService.createGame(player));
    }

    @PostMapping("/connect")
    public ResponseEntity<Game> connect(@RequestBody Connect request) throws InvalidException, InvalidGame {
        log.info("connect request: {}", request);
        try {
            return ResponseEntity.ok(gameService.connectToGame(request.getPlayer(), request.getGameId()));
        }
        catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Game Not Found", ex);
        }
    }

    @PostMapping("/connect/random")
    public ResponseEntity<Game> connectRandom(@RequestBody Player player) throws NotFound {
        log.info("connect random {}", player);
        return ResponseEntity.ok(gameService.connectToRandomGame(player));
    }

    @PostMapping("/play")
    public ResponseEntity<Game> play(@RequestBody Play request) throws InvalidException, NotFound {
        log.info("Play {}", request);
        return ResponseEntity.ok(gameService.play(request));
    }

    @GetMapping("/players")
    public ResponseEntity<Iterable<Player>> listAll(Model model) {
        Iterable<Player> listStudents = playerRepo.findAll();
        model.addAttribute("listStudents", listStudents);

        return ResponseEntity.ok(listStudents);
    }

    @PostMapping("/player")
    public String addPlayer(@RequestBody Player request) throws InvalidException {
        playerRepo.save(request);
        return "saved";
    }

    @GetMapping("/player/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable Long id) {
        Optional<Player> player = playerRepo.findById(id);
        try {
            return ResponseEntity.ok(player.get());
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Game Not Found", ex);
        }
    }
}

