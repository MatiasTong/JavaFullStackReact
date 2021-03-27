package com.mt.GuessingGame.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class Guess {
    private int guessId;
    private String guess;
    private LocalDateTime time;
    private String result;
    private int gameId;



    public int getGuessId() {
        return guessId;
    }

    public void setGuessId(int guessId) {
        this.guessId = guessId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guess guess1 = (Guess) o;
        return guessId == guess1.guessId &&
                gameId == guess1.gameId &&
                Objects.equals(guess, guess1.guess) &&
                Objects.equals(time, guess1.time) &&
                Objects.equals(result, guess1.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guessId, guess, time, result, gameId);
    }

    @Override
    public String toString() {
        return "Guess{" +
                "guessId=" + guessId +
                ", guess='" + guess + '\'' +
                ", time=" + time +
                ", result='" + result + '\'' +
                ", gameId=" + gameId +
                '}';
    }
}
