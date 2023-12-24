package com.picksa.picksaserver.global.exception;

import com.picksa.picksaserver.global.response.DefaultErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<DefaultErrorResponse> handleEntityNotFoundException(EntityNotFoundException exception) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(DefaultErrorResponse.from(exception.getMessage()));    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<DefaultErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(DefaultErrorResponse.from(exception.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<DefaultErrorResponse> handleAccessDeniedException(AccessDeniedException exception) {
        return ResponseEntity.status(FORBIDDEN)
                .body(DefaultErrorResponse.from(exception.getMessage()));
    }

}
