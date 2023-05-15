package com.academy.hdemo.exception;

public class InsufficientAmountException extends RuntimeException{
    public InsufficientAmountException(String errorMessage){
        super(errorMessage);
    }
}
