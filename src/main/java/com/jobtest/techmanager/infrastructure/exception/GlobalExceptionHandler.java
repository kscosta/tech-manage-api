package com.jobtest.techmanager.infrastructure.exception;

import com.jobtest.techmanager.controller.representation.response.DefaultApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Classe responsável por gerenciar globalmente as exceções da aplicação
 *
 * @version 1.0
 * @since Java 21
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<DefaultApiResponse<String>> constraintViolationExceptionHandler(
            ConstraintViolationException constraintViolationException) {

        AtomicReference<String> message = new AtomicReference<>("");

        constraintViolationException.getConstraintViolations().forEach(constraintViolation ->
                message.set(Objects.isNull(constraintViolation.getMessage()) ? "" : constraintViolation.getMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new DefaultApiResponse<>(LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        String.valueOf(message).isEmpty() ? constraintViolationException.getMessage() :
                                String.valueOf(message),
                        null)
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultApiResponse<String>> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException methodArgumentNotValidException) {

        String message = null;
        if (Objects.nonNull(methodArgumentNotValidException)) {
            var error = methodArgumentNotValidException.getFieldError();
            message = Objects.isNull(error) ? null : error.getDefaultMessage();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new DefaultApiResponse<>(LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        message,
                        null)
        );
    }

}
