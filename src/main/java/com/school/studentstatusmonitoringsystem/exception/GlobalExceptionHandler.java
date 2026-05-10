package com.school.studentstatusmonitoringsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // =========================
    // VALIDATION ERRORS
    // =========================

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationErrors(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return errors;
    }

    // =========================
    // CUSTOM RUNTIME ERRORS
    // =========================

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleRuntimeError(RuntimeException ex) {

        Map<String, String> error = new HashMap<>();

        error.put("message", ex.getMessage());

        return error;
    }

    // =========================
    // UNKNOWN ERRORS
    // =========================

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleGeneralError(Exception ex) {

        Map<String, String> error = new HashMap<>();

        error.put("message", "Something went wrong");

        return error;
    }
}