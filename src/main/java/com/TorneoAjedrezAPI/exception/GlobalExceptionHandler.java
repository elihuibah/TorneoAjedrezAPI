package com.TorneoAjedrezAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CampoIncompleto.class)
    public ResponseEntity<String> handleNombreIncompleto(CampoIncompleto nombreIncompleto) {
        return new ResponseEntity<>(nombreIncompleto.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleELOInsuficiente(Exception eloInsuficiente) {
        return new ResponseEntity<>(eloInsuficiente.getMessage(), HttpStatus.BAD_REQUEST);
    }
}