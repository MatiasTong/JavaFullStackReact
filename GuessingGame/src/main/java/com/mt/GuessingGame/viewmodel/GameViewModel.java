package com.mt.GuessingGame.viewmodel;

import com.mt.GuessingGame.dto.Game;

public class GameViewModel {
    private int gameId;
    private String answer;
    private boolean isFinished;

    public GameViewModel(Game game) {
        if (game.isFinished() == false) {
            setAnswer("****");
        } else {
            setAnswer(game.getAnswer());
        }
        setGameId(game.getGameId());

        setFinished(game.isFinished());
    }

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
}
