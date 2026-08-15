/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.exceptions.RestControllerExceptionHandler
 *  jakarta.servlet.http.HttpServletRequest
 *  org.springframework.http.HttpStatus
 *  org.springframework.http.HttpStatusCode
 *  org.springframework.http.ResponseEntity
 *  org.springframework.http.converter.HttpMessageNotReadableException
 *  org.springframework.web.bind.MethodArgumentNotValidException
 *  org.springframework.web.bind.annotation.ExceptionHandler
 *  org.springframework.web.bind.annotation.RestControllerAdvice
 */
package com.empay.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {
    @ExceptionHandler(value={MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage()).toList();
        body.put("errors", errors);
        return new ResponseEntity(body, (HttpStatusCode)HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {
        LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", "Required request body is missing");
        body.put("path", request.getRequestURI());
        return new ResponseEntity(body, (HttpStatusCode)HttpStatus.BAD_REQUEST);
    }
}

