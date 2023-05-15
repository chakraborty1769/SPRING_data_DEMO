package com.academy.hdemo.exception;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
