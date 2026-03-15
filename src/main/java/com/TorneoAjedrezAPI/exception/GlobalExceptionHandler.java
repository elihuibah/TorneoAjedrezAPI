package com.TorneoAjedrezAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException illegalArgException) {
        return new ResponseEntity<>(illegalArgException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecursoNoEncontrado.class)
    public ResponseEntity<String> handleNotFoundArgument(RecursoNoEncontrado noEncontrado){
        return new ResponseEntity<>(noEncontrado.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PartidaActiva.class)
    public ResponseEntity<String> handlePartidaActiva(PartidaActiva partidaActiva) {
        return new ResponseEntity<>(partidaActiva.getMessage(), HttpStatus.BAD_REQUEST);
    }

}