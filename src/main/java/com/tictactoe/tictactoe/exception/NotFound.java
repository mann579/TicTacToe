package com.tictactoe.tictactoe.exception;

public class NotFound extends Exception {

    private String message;

    public NotFound(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}