/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.exceptions.RestControllerExceptionHandler
 *  com.empay.exceptions.UnauthorizedException
 *  com.empay.exceptions.ValidationException
 *  org.springframework.http.HttpStatus
 *  org.springframework.http.HttpStatusCode
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.MethodArgumentNotValidException
 *  org.springframework.web.bind.annotation.ExceptionHandler
 *  org.springframework.web.bind.annotation.RestControllerAdvice
 */
package com.empay.exceptions;

import com.empay.exceptions.UnauthorizedException;
import com.empay.exceptions.ValidationException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {
    @ExceptionHandler(value={MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage()).toList();
        body.put("errors", errors);
        return new ResponseEntity(body, (HttpStatusCode)HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={ValidationException.class})
    public ResponseEntity<Map<String, Object>> fileValidation(ValidationException ex) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("message", ex.getErrorMessage());
        return new ResponseEntity(response, (HttpStatusCode)HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={UnauthorizedException.class})
    public ResponseEntity<Map<String, Object>> handleUnauthorizedException(UnauthorizedException ex) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", HttpStatus.UNAUTHORIZED.value());
        response.put("error", HttpStatus.UNAUTHORIZED);
        response.put("message", ex.getErrorMessage());
        return new ResponseEntity(response, (HttpStatusCode)HttpStatus.UNAUTHORIZED);
    }
}

