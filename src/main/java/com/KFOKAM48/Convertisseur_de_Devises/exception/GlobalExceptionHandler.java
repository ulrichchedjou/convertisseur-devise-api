package com.KFOKAM48.Convertisseur_de_Devises.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidCurrencyException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCurrency(InvalidCurrencyException ex) {
        return new ResponseEntity<>(new ErrorResponse(400, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ExternalApiException.class})
    public ResponseEntity<ErrorResponse> handleExternalApi(ExternalApiException ex) {
        return new ResponseEntity<>(new ErrorResponse(503, ex.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(jakarta.validation.ConstraintViolationException ex) {
        StringBuilder message = new StringBuilder("Validation error(s): ");
        ex.getConstraintViolations().forEach(violation ->
            message.append(violation.getPropertyPath()).append(": ").append(violation.getMessage()).append("; ")
        );
        return new ResponseEntity<>(new ErrorResponse(400, message.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return new ResponseEntity<>(
            new ErrorResponse(500, "An unexpected error occurred: " + ex.getMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}