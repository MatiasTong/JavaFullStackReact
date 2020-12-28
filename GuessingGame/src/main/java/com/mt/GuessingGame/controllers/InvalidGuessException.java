package com.mt.GuessingGame.controllers;

public class InvalidGuessException extends Exception {
    public InvalidGuessException(String message){
        super(message);
    }
    public InvalidGuessException(String message, Throwable cause){
        super(message, cause);
    }
}
