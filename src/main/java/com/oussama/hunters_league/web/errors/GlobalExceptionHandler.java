package com.oussama.hunters_league.web.errors;

import com.oussama.hunters_league.exception.UserAlreadyExistException;
import com.oussama.hunters_league.exception.UserNotFoundException;
import jakarta.persistence.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException exceptions){
        Map<String,String> errors=new HashMap<>();
        exceptions.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(),error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleUserNotFoundException(UserNotFoundException exception){
        Map<String,String> error=new HashMap<>();
        error.put("error",exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Map<String,String>> handleUserAlreadyExistException(UserAlreadyExistException exception){
        Map<String,String> error=new HashMap<>();
        error.put("error",exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
