package com.mt.GuessingGame.dao;
import com.mt.GuessingGame.dto.Game;
import com.mt.GuessingGame.dto.Guess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class GuessDaoImpl implements GuessDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GuessDaoImpl(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Guess add(Guess guess) {
        final String sql = "INSERT INTO Guess(Guess, Result, GameId) VALUES (?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, guess.getGuess());
            statement.setString(2, guess.getResult());
            statement.setInt(3, guess.getGameId());
            return statement;
        }, keyHolder);

        guess.setGuessId(keyHolder.getKey().intValue());
        //time is automatically recorded in the database, set time in the returned guess object
        guess.setTime(findById(guess.getGuessId()).getTime());

        return guess;
    }

    @Override
    public List<Guess> getAll() {
        final String sql = "SELECT GuessId, Time, Guess, Result, GameId FROM Guess;";
        List<Guess> guesses = jdbcTemplate.query(sql, new GuessMapper());
        return guesses;
    }

    @Override
    public Guess findById(int id) {
        final String sql = "SELECT GuessId, Time, Guess, Result, GameId FROM Guess WHERE GuessId = ?;";
        return jdbcTemplate.queryForObject(sql, new GuessMapper(), id);
    }

    @Override
    public boolean update(Guess guess) {
        final String sql = "UPDATE Guess SET Guess = ?, Time = ?, Result = ?, GameId = ? WHERE GuessId = ?;";
        int guessId = guess.getGuessId();
        int gameId = guess.getGameId();
        String guessInput = guess.getGuess();
        LocalDateTime time = guess.getTime();
        String result = guess.getResult();

        return jdbcTemplate.update(sql, guessInput, time, result, gameId, guessId) > 0;
    }

    @Override
    public void deleteById(int id) {
        final String sql = "DELETE FROM Guess WHERE GuessId = ?;";
        jdbcTemplate.update(sql, id);
    }

    public List<Guess> getGuessesForGame(int gameId){
        final String sql = "SELECT GuessId, Time, Guess, Result, GameId FROM Guess WHERE GameId = ?;";
        List<Guess> guesses = jdbcTemplate.query(sql, new GuessMapper(), gameId);
        return guesses;
    }
//
//    private Guess getGameForGuess(Guess guess){
//        final String sql = "SELECT GameId, Answer, isFinished FROM Game JOIN Guess ON Guess.GameId = GameId " +
//                "WHERE GuessId = ?;";
//        Game game = jdbcTemplate.queryForObject(sql, new GameDaoImpl.GameMapper(), guess.getGuessId());
//        guess.setGameId(game.getGameId());
//        return guess;
//    }

//    private List<Guess> getGameForAllGuesses(List<Guess> guesses){
//        for(Guess guess: guesses){
//            getGameForGuess(guess);
//        }
//        return guesses;
//    }


    private static final class GuessMapper implements RowMapper<Guess> {

        @Override
        public Guess mapRow(ResultSet resultSet, int i) throws SQLException {
            Guess guess = new Guess();
            guess.setGuessId(resultSet.getInt("GuessId"));
            guess.setGameId(resultSet.getInt("GameId"));
            guess.setGuess(resultSet.getString("Guess"));
            guess.setTime(resultSet.getObject("Time", LocalDateTime.class));
            guess.setResult(resultSet.getString("Result"));

            return guess;
        }
    }
}
