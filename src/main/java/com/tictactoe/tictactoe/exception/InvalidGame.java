package com.tictactoe.tictactoe.exception;

public class InvalidGame extends Exception {

    private String message;

    public InvalidGame(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
