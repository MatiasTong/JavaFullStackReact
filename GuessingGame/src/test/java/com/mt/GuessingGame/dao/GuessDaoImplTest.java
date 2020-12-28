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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GuessDaoImplTest {

    @Autowired
    GameDaoImpl gameDao;

    @Autowired
    GuessDaoImpl guessDao;

    public GuessDaoImplTest() {
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
     * Test of create method, of class RoundDAOImpl.
     */
    @Test
    public void testAddAndFindById() {
        setUp();

        Game game = new Game();
        game.setAnswer("5678");
        game = gameDao.add(game);

        Guess guess = new Guess();
        guess.setGameId(game.getGameId());
        guess.setGuess("0456");
        guess.setResult("e:0 p:0");
        guess.setTime(LocalDateTime.now());
        guess = guessDao.add(guess);

        Guess guessFromDao = guessDao.findById(guess.getGuessId());

        assertEquals(guess.getGuess(), guessFromDao.getGuess());
    }

    /**
     * Test of readAll method, of class RoundDAOImpl.
     */
    @Test
    public void testGetAll() {
        setUp();

        List<Guess> roundList = new ArrayList<>();
        Game game = new Game();
        game.setAnswer("5678");
        game = gameDao.add(game);

        Guess round = new Guess();
        round.setGameId(game.getGameId());
        round.setGuess("0456");
        round.setResult("e:0 p:0");
        round = guessDao.add(round);

        Guess round2 = new Guess();
        round2.setGameId(game.getGameId());
        round2.setGuess("1056");
        round2.setResult("e:0 p:0");
        round2 = guessDao.add(round2);

        List<Guess> guessList2 = guessDao.getAll();
        assertEquals(guessList2.size(), 2);
        assertTrue(guessList2.contains(round));
        assertTrue(guessList2.contains(round2));
    }

    /**
     * Test of readById method, of class RoundDAOImpl.
     */
    @Test
    public void testUpdate() {
        setUp();
        List<Guess> roundList = new ArrayList<>();

        Game game = new Game();
        game.setAnswer("5678");
        game = gameDao.add(game);

        Guess guess = new Guess();
        guess.setGameId(game.getGameId());
        guess.setGuess("0456");
        guess.setResult("e:0 p:0");
        guess = guessDao.add(guess);

        Guess updatedGuess = new Guess();
        updatedGuess.setGuess("0123");
        updatedGuess.setResult("e:2 p:0");
        updatedGuess.setGuessId(guess.getGuessId());
        updatedGuess.setGameId(game.getGameId());
        guessDao.update(updatedGuess);

        Guess updatedGuessFromDao = guessDao.findById(updatedGuess.getGuessId());

        assertEquals(updatedGuess, updatedGuessFromDao);

    }

    /**
     * Test of delete method, of class RoundDAOImpl.
     */
    @Test
    public void testDelete() {
        setUp();

        Game game = new Game();
        game.setAnswer("5678");
        game = gameDao.add(game);

        Guess guess = new Guess();
        guess.setGameId(game.getGameId());
        guess.setGuess("0456");
        guess.setResult("e:0 p:0");
        guess = guessDao.add(guess);
        guessDao.deleteById(guess.getGuessId());

        List <Guess> guessListFromDao = guessDao.getAll();

        assertEquals(guessListFromDao.size(), 0);
    }

}
