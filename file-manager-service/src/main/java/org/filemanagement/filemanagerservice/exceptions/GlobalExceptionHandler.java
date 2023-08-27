package org.filemanagement.filemanagerservice.exceptions;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.filemanagement.filemanagerservice.models.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> handleValidationExceptions(MethodArgumentNotValidException ex){
        log.info("exception {}", ex.getBindingResult());
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(err -> {

            erros.put(((FieldError) err).getField(), err.getDefaultMessage());
        });
        return erros;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> handleValidationExceptions(ConstraintViolationException ex){
        log.info("exception {}", ex.getConstraintViolations());
        Map<String, String> erros = new HashMap<>();
        ex.getConstraintViolations().forEach(err -> {
            erros.put(err.getPropertyPath().toString(),err.getMessage());
        });
        return erros;
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class )
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public HttpResponse handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex){
        return HttpResponse.builder().message("Not Allowed").status(HttpStatus.FORBIDDEN).build();
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
        return HttpResponse.builder().message("Not Allowed").status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler(value = FileSizeLimitExceededException.class )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse handleFileSizeLimitExceededException(FileSizeLimitExceededException ex){
        return HttpResponse.builder().message("Exceeded File Size Limit").status(HttpStatus.BAD_REQUEST).build();
    }






    @ExceptionHandler(value = {DocumentNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpResponse handleDocumentNotFoundException(DocumentNotFoundException ex){
        return HttpResponse.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(value = {DocumentNotOwnerException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public HttpResponse handleDocumentNotOwnerException(DocumentNotOwnerException ex){
        return HttpResponse.builder().message(ex.getMessage()).status(HttpStatus.FORBIDDEN).build();
    }

    @ExceptionHandler(value = {FileNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpResponse handleFileNotFoundException(FileNotFoundException ex){
        return HttpResponse.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND).build();
    }



}
