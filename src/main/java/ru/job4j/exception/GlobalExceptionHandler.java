package ru.job4j.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    private final ObjectMapper objectMapper;

    @Autowired
    public GlobalExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleModel(MethodArgumentNotValidException e) {
        log.error("Model validity error: {}", e.getMessage());
        return ResponseEntity.badRequest()
                .body(
                        e.getFieldErrors().stream()
                                .map(f -> Map.of(
                                        f.getField(),
                                        String.format("%s. Actual value: %s", f.getDefaultMessage(), f.getRejectedValue())
                                ))
                                .collect(Collectors.toList())
                );
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<String> handleExceptionSql(Exception e, WebRequest request) {
        log.error(e.getMessage());
        return new ResponseEntity<>(
                "The uniqueness of the data in the table is violated!", HttpStatus.BAD_REQUEST
        );
    }
}
