package com.mt.GuessingGame.dao;

import com.mt.GuessingGame.dto.Game;
import com.mt.GuessingGame.dto.Guess;

import java.util.List;

public interface GuessDao {
    Guess add(Guess guess);

    List<Guess> getAll();

    Guess findById(int id);

    // true if item exists and is updated
    boolean update(Guess guess);

    // true if item exists and is deleted
    void deleteById(int id);

    public List<Guess> getGuessesForGame(int gameId);
}
