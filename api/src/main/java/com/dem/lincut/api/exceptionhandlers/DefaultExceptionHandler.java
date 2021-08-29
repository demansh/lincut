package com.dem.lincut.api.exceptionhandlers;

import com.dem.lincut.core.exceptions.CoreException;
import com.dem.lincut.core.exceptions.InvalidParameterException;
import com.dem.lincut.core.exceptions.LinkNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler(CoreException.class)
    public ResponseEntity<ExceptionResource> handleException(RuntimeException e) {
        ExceptionResource response = new ExceptionResource(e.getMessage());
        if (e instanceof LinkNotFoundException) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        if (e instanceof InvalidParameterException) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
