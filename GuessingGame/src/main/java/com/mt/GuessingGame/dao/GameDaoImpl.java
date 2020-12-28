package com.mt.GuessingGame.dao;


import com.mt.GuessingGame.dto.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class GameDaoImpl implements GameDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Game add(Game game) {
        final String sql = "INSERT INTO Game(Answer) VALUES (?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, game.getAnswer());
            return statement;
        }, keyHolder);

        game.setGameId(keyHolder.getKey().intValue());

        return findById(game.getGameId());
    }

    @Override
    public List<Game> getAll() {
        final String sql = "SELECT GameId, Answer, IsFinished FROM Game;";
        return jdbcTemplate.query(sql, new GameMapper());
    }

    @Override
    public Game findById(int id) {
        final String sql = "SELECT GameId, Answer, IsFinished FROM Game WHERE GameId = ?;";
        return jdbcTemplate.queryForObject(sql, new GameMapper(), id);
    }

    @Override
    public boolean update(Game game) {
       final String sql = "UPDATE Game SET Answer = ?, IsFinished=? WHERE GameId = ?;";
       return jdbcTemplate.update(sql, game.getAnswer(), game.isFinished(), game.getGameId()) > 0;
    }

    @Override
    public void deleteById(int id) {
        final String sql1 = "DELETE FROM Guess WHERE GameId = ?;";
        final String sql2 = "DELETE FROM Game WHERE GameId = ?;";
        jdbcTemplate.update(sql1, id);
        jdbcTemplate.update(sql2, id);
    }


    public static final class GameMapper implements RowMapper<Game> {
        @Override
        public Game mapRow(ResultSet resultSet, int i) throws SQLException {
            Game game = new Game();
            game.setGameId(resultSet.getInt("GameId"));
            game.setAnswer(resultSet.getString("Answer"));
            game.setFinished(resultSet.getBoolean("IsFinished"));
            return game;
        }
    }
}
