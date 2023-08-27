package org.filesmanagement.authenticationservice.security.exceptions;

import org.filesmanagement.authenticationservice.model.exceptions.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BadCredentialsException.class )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handleSQLIntegrityConstraintViolationException(BadCredentialsException ex){
        return ResponseError.builder().message("bad credentials").status(HttpStatus.BAD_REQUEST.value()).build();
    }
}
