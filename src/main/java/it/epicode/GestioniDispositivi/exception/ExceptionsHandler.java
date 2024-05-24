package it.epicode.GestioniDispositivi.exception;

import it.epicode.GestioniDispositivi.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DipendenteNotFoundException.class)
    public ResponseEntity<Object> AutoreNotFoundHandler(DipendenteNotFoundException e){
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setErrorStatus(HttpStatus.NOT_FOUND);
        error.setErrorTime(LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DispositivoNotFoundException.class)
    public ResponseEntity<Object> BlogNotFoundHandler(DispositivoNotFoundException e){
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setErrorStatus(HttpStatus.NOT_FOUND);
        error.setErrorTime(LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> BlogNotFoundHandler(BadRequestException e){
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setErrorStatus(HttpStatus.BAD_REQUEST);
        error.setErrorTime(LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
