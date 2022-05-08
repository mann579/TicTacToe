package com.tictactoe.tictactoe;

import com.tictactoe.tictactoe.model.Game;
import com.tictactoe.tictactoe.model.Player;
import com.tictactoe.tictactoe.service.GameService;
import com.tictactoe.tictactoe.utils.Winner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TictactoeApplicationTests {

	@Autowired
	GameService service;

	@Test
	void testGame() {
		assertNotNull(service.createGame(new Player()));
	}

	@Test
	void testConnectRandom() {
		try {
			Game game = service.createGame(new Player());
			Game game2 = service.connectToRandomGame(new Player());
			assertNotNull(game2);
		}
		catch (Exception ex) {
			fail();
		}
	}

	@Test
	void testFindWinner() {
		int[][] mockArray = { {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
		int winner = Winner.findWinner(mockArray);
		assertEquals(1, winner);
	}
}
