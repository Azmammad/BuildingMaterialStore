package com.example.buildingmaterialstore.model.exception;

import com.example.buildingmaterialstore.model.exception.handle.InactiveUserException;
import com.example.buildingmaterialstore.model.exception.handle.ResourceNotFoundException;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(InactiveUserException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN) //403
    public ResponseEntity<ErrorResponse> handleInactiveUserException(InactiveUserException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.FORBIDDEN);

    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(String message, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setMessage(message);
        errorResponse.setStatus(status.value());
        return new ResponseEntity<>(errorResponse, status);
    }

    public static class ErrorResponse {
        private LocalDateTime timestamp;
        private String message;
        private int status;

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
