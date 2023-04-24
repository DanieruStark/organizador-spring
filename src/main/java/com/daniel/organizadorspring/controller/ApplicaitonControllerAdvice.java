package com.daniel.organizadorspring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.daniel.exception.RecordNotFoundException;

@RestControllerAdvice
public class ApplicaitonControllerAdvice {
    
    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundExcepiton(RecordNotFoundException ex){
        return ex.getMessage();
    }
}
