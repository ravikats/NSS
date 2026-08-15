/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.exceptions.ErrorDetailsVO
 *  com.empay.exceptions.ResponseVO
 *  com.empay.exceptions.RestControllerExceptionHandler
 *  com.empay.exceptions.ValidationExceptionVO
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.springframework.http.HttpStatus
 *  org.springframework.http.converter.HttpMessageNotReadableException
 *  org.springframework.validation.BindingResult
 *  org.springframework.validation.FieldError
 *  org.springframework.web.HttpRequestMethodNotSupportedException
 *  org.springframework.web.bind.MethodArgumentNotValidException
 *  org.springframework.web.bind.MissingServletRequestParameterException
 *  org.springframework.web.bind.annotation.ExceptionHandler
 *  org.springframework.web.bind.annotation.ResponseStatus
 *  org.springframework.web.bind.annotation.RestControllerAdvice
 *  org.springframework.web.servlet.NoHandlerFoundException
 */
package com.empay.exceptions;

import com.empay.exceptions.ErrorDetailsVO;
import com.empay.exceptions.ResponseVO;
import com.empay.exceptions.ValidationExceptionVO;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class RestControllerExceptionHandler {
    private static final Logger log = LogManager.getLogger(RestControllerExceptionHandler.class);

    @ExceptionHandler(value={Exception.class})
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseVO exceptionHandler(Exception e) {
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        log.debug("INTERNAL_SERVER_ERROR :: " + writer.toString());
        log.debug("INTERNAL_SERVER_ERROR", (Throwable)e);
        return this.getExceptionData("INTERNAL_SYSTEM_ERROR; failed to process the request");
    }

    @ExceptionHandler(value={HttpMessageNotReadableException.class})
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    public ResponseVO notReadableExceptionHandler(HttpMessageNotReadableException e) {
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        log.debug("Exception in BODY is :: " + writer.toString());
        log.debug("ERROR IN REQUEST BODY" + String.valueOf(e));
        return this.getExceptionData("Invalid request body;Please provide a valid body");
    }

    @ExceptionHandler(value={MissingServletRequestParameterException.class})
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    public ResponseVO missingRequestParamExceptionHandler(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        return this.getExceptionData("INVALID URL; missing parameter " + name);
    }

    @ExceptionHandler(value={MethodArgumentNotValidException.class})
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    public ValidationExceptionVO pojoExceptionHandler(MethodArgumentNotValidException ex) {
        return this.getValidationError(ex);
    }

    @ExceptionHandler(value={NoHandlerFoundException.class})
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public ResponseVO requestHandlingNoHandlerFound(NoHandlerFoundException ex) {
        return this.getExceptionData("Endpoint Not Found;" + ex.getMessage());
    }

    @ExceptionHandler(value={HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(value=HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseVO methodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        return this.getExceptionData("Method Not Allowed;" + ex.getMessage());
    }

    private ResponseVO getExceptionData(String message) {
        log.debug("Exception message " + message);
        ResponseVO exceptionResponseVO = new ResponseVO();
        String[] exceptionMsg = message.split(";");
        exceptionResponseVO.setName(exceptionMsg[0]);
        exceptionResponseVO.setMessage(exceptionMsg[1]);
        UUID uuid = UUID.randomUUID();
        exceptionResponseVO.setDebugId(uuid.toString());
        log.debug("RESPONSE : " + String.valueOf(exceptionResponseVO));
        return exceptionResponseVO;
    }

    private ValidationExceptionVO getValidationError(MethodArgumentNotValidException ex) {
        UUID uuid = UUID.randomUUID();
        ValidationExceptionVO validationExe = new ValidationExceptionVO();
        ArrayList<ErrorDetailsVO> erroDetails = new ArrayList<ErrorDetailsVO>();
        ErrorDetailsVO error = null;
        validationExe.setName("VALIDATION_ERROR");
        validationExe.setMessage("Validation Error");
        validationExe.setDebugId(uuid.toString());
        BindingResult result = ex.getBindingResult();
        List fieldErrors = result.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            error = new ErrorDetailsVO();
            error.setField(fieldError.getField().replaceAll("\\.", "/"));
            error.setLocation("body");
            error.setIssue(fieldError.getDefaultMessage());
            error.setValue(String.valueOf(fieldError.getRejectedValue()));
            erroDetails.add(error);
            error = null;
        }
        validationExe.setErrorDetails(erroDetails);
        log.debug("RESPONSE : " + String.valueOf(validationExe));
        return validationExe;
    }
}

