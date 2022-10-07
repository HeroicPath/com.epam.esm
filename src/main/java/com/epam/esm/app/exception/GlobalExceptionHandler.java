package com.epam.esm.app.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private LocalThrowableMessage localThrowableMessage;

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> methodArgumentTypeMismatchExceptionHandler() {
        localThrowableMessage = new LocalThrowableMessage(HttpStatus.BAD_REQUEST, "You entered wrong type of input for your request");
        return new ResponseEntity<>(localThrowableMessage, localThrowableMessage.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        localThrowableMessage = new LocalThrowableMessage(status, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, localThrowableMessage, headers, status, request);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<Object> invalidFormatExceptionHandler() {
        localThrowableMessage = new LocalThrowableMessage(HttpStatus.BAD_REQUEST, "You provided wrong data type for POST request");
        return new ResponseEntity<>(localThrowableMessage, localThrowableMessage.getHttpStatus());
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException (){
        LocalThrowableMessage localThrowableMessage = new LocalThrowableMessage(HttpStatus.NOT_FOUND, "No object found by your request");
        return new ResponseEntity<>(localThrowableMessage, localThrowableMessage.getHttpStatus());
    }

    /*@ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Object> duplicateKeyExceptionHandler(DuplicateKeyException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        return handleExceptionInternal()
        return new LocalException("There is already an entity with such name, please enter another one");
    }*/

    /*@ExceptionHandler(Exception.class)
    public @ResponseBody
    LocalException generalExceptionHandler(){
        return new LocalException("Something went wrong");
    }*/
}