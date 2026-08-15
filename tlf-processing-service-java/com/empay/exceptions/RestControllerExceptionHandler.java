// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.exceptions;

import org.springframework.validation.FieldError;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler
{
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<Object> handleValidationExceptions(final MethodArgumentNotValidException ex) {
        final Map<String, Object> body = new LinkedHashMap<String, Object>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        final List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage()).toList();
        body.put("error", errors);
        return (ResponseEntity<Object>)new ResponseEntity((Object)body, (HttpStatusCode)HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler({ ValidationException.class })
    public ResponseEntity<Map<String, Object>> fileValidation(final ValidationException ex) {
        final Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", ex.getErrorMessage());
        return (ResponseEntity<Map<String, Object>>)new ResponseEntity((Object)response, (HttpStatusCode)HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler({ HttpServerErrorException.InternalServerError.class })
    public ResponseEntity<Map<String, Object>> internalServerValidation(final HttpServerErrorException.InternalServerError ex) {
        final Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", ex.getMessage());
        return (ResponseEntity<Map<String, Object>>)new ResponseEntity((Object)response, (HttpStatusCode)HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler({ NullPointerException.class })
    public ResponseEntity<Map<String, Object>> nullPointerValidation(final NullPointerException ex) {
        final Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", ex.getMessage());
        return (ResponseEntity<Map<String, Object>>)new ResponseEntity((Object)response, (HttpStatusCode)HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    public ResponseEntity<Map<String, Object>> requestMethodNotSupportedException(final HttpRequestMethodNotSupportedException ex) {
        final Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", ex.getMessage());
        return (ResponseEntity<Map<String, Object>>)new ResponseEntity((Object)response, (HttpStatusCode)HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler({ UnexpectedRollbackException.class })
    public ResponseEntity<Map<String, Object>> unexpectedRollbackException(final UnexpectedRollbackException ex) {
        final Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", ex.getMessage());
        return (ResponseEntity<Map<String, Object>>)new ResponseEntity((Object)response, (HttpStatusCode)HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler({ UnrecognizedPropertyException.class })
    public ResponseEntity<Map<String, Object>> UnrecognizedPropertyException(final UnrecognizedPropertyException ex) {
        final Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", ex.getMessage());
        return (ResponseEntity<Map<String, Object>>)new ResponseEntity((Object)response, (HttpStatusCode)HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler({ JpaSystemException.class })
    public ResponseEntity<Map<String, Object>> jpaSystemException(final JpaSystemException ex) {
        final Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", ex.getMessage());
        return (ResponseEntity<Map<String, Object>>)new ResponseEntity((Object)response, (HttpStatusCode)HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler({ HttpMessageNotReadableException.class })
    public ResponseEntity<Map<String, Object>> messageNotReadable(final HttpMessageNotReadableException ex) {
        final Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", "INVALID INPUT ; Invalid Json");
        return (ResponseEntity<Map<String, Object>>)new ResponseEntity((Object)response, (HttpStatusCode)HttpStatus.BAD_REQUEST);
    }
}
