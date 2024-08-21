package com.bookstoreapi.bookstoreapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bookstoreapi.bookstoreapi.error.ErrorDetails;
import com.bookstoreapi.bookstoreapi.exceptions.NoSuchBookExist;

@ControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(value = NoSuchBookExist.class)
    public ResponseEntity<ErrorDetails> handleNotFoundException(NoSuchBookExist ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDetails(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }
}
