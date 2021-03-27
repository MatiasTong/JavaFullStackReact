package com.mt.GuessingGame.dao;

import com.mt.GuessingGame.TestApplicationConfiguration;
import com.mt.GuessingGame.dto.Game;
import com.mt.GuessingGame.dto.Guess;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GameDaoImplTest {

    @Autowired
    GameDaoImpl gameDao;

    @Autowired
    GuessDaoImpl guessDao;

    public GameDaoImplTest() {
    }

    @BeforeEach
    public void setUp() {
        List<Guess> guesses = guessDao.getAll();
        for (Guess guess : guesses) {
            guessDao.deleteById(guess.getGuessId());
        }

        List<Game> games = gameDao.getAll();
        for (Game game : games) {
            gameDao.deleteById(game.getGameId());
        }
    }

    /**
     * Test of create method, of class GameDAOImpl.
     */
    @Test
    public void testCreateAndReadById() {
        setUp();

        Game game = new Game();
        game.setAnswer("5678");
        game = gameDao.add(game);


        Game gameFromDao = gameDao.findById(game.getGameId());

        assertEquals(game, gameFromDao);
    }

    /**
     * Test of readAll method, of class GameDAOImpl.
     */
    @Test
    public void testGetAll() {
        setUp();

        Game game = new Game();
        game.setAnswer("5678");
        game = gameDao.add(game);


        Game game3 = new Game();
        game3.setAnswer("8970");
        game3 = gameDao.add(game3);


        List<Game> gameList = gameDao.getAll();
        assertEquals(gameList.size(), 2);
        assertTrue(gameList.contains(game));
        assertTrue(gameList.contains(game3));
    }



    /**
     * Test of update method, of class GameDAOImpl.
     */
    @Test
    public void testUpdate() {
        setUp();

        Game game = new Game();
        game.setAnswer("5678");
        game = gameDao.add(game);

        Game updatedGame = new Game();
        updatedGame.setAnswer("1234");
        updatedGame.setGameId(game.getGameId());
        updatedGame.setFinished(false);

        gameDao.update(updatedGame);

        Game updatedGameFromDao = gameDao.findById(updatedGame.getGameId());

        assertEquals(updatedGame, updatedGameFromDao);

    }

    /**
     * Test of delete method, of class GameDAOImpl.
     */
    @Test
    public void testDelete() {
        setUp();

        Game game = new Game();
        game.setAnswer("5678");
        game = gameDao.add(game);


        gameDao.deleteById(game.getGameId());

        List<Game> gameList =  gameDao.getAll();

        assertEquals(gameList.size(), 0);

    }

}