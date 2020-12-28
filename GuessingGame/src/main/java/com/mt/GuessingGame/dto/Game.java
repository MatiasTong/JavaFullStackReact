package com.mt.GuessingGame.dto;

import java.util.Objects;

public class Game {
    private int gameId;
    private String answer;
    private boolean isFinished;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return gameId == game.gameId &&
                isFinished == game.isFinished &&
                Objects.equals(answer, game.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, answer, isFinished);
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", answer='" + answer + '\'' +
                ", isFinished=" + isFinished +
                '}';
    }
}
