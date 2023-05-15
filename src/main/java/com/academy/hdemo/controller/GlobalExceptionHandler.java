package com.academy.hdemo.controller;

import com.academy.hdemo.exception.AccountNotFoundException;
import com.academy.hdemo.exception.InsufficientAmountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({InsufficientAmountException.class, AccountNotFoundException.class})
    public ResponseEntity<Object> returnNotFoundException(Exception exception){
        if (exception instanceof InsufficientAmountException){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }

        else{
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}
