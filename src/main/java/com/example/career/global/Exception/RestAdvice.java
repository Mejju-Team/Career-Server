package com.example.career.global.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice("com.example.career.domain.community.Controller")
public class RestAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> ExceptionHandler(Exception e) {
        log.info("Exception msg = {}", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
