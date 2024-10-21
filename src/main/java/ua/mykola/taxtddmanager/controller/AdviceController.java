package ua.mykola.taxtddmanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.mykola.taxtddmanager.exception.ValidateException;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler
    public ResponseEntity<String> exceptionHandler(ValidateException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
}
