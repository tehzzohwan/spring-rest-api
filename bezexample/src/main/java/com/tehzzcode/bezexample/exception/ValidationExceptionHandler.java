package com.tehzzcode.bezexample.exception;

import com.tehzzcode.bezexample.payload.DefaultResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultResponseDto> notValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        DefaultResponseDto response = new DefaultResponseDto();
        List<String> errors = new ArrayList<>();

        ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));

        response.setStatusCode("99");
        response.setMessage("Sorry bad request");
        response.setErrorMessage(String.join(", ", errors));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
