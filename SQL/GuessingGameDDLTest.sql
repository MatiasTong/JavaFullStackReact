DROP DATABASE IF EXISTS GuessingGameTest;
CREATE DATABASE GuessingGameTest;
USE GuessingGameTest ;

CREATE TABLE Game (
  GameId INT NOT NULL auto_increment,
  Answer CHAR(4) NOT NULL,
  IsFinished boolean NOT NULL,
  PRIMARY KEY (`GameId`));


CREATE TABLE Guess (
  GuessId INT NOT NULL auto_increment,
  Guess CHAR(4) NOT NULL,
  Time DATETIME default current_timestamp,
  Result CHAR(7) NOT NULL,
  GameId INT NOT NULL,
  PRIMARY KEY (GuessId),
  FOREIGN KEY fk_Guess_Game(GameId)
    REFERENCES Game(GameId)
);