package com.ic045.sistemaacademico.exception;

import com.ic045.sistemaacademico.exception.custom.BadRequestException;
import com.ic045.sistemaacademico.exception.custom.NotCreatedException;
import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleException(NotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(buildErrorResponse(HttpStatus.NOT_FOUND, ex));
    }
    @ExceptionHandler(value = {NotCreatedException.class})
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public ResponseEntity<Object> handleException(NotCreatedException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_IMPLEMENTED)
                .body(buildErrorResponse(HttpStatus.NOT_IMPLEMENTED,ex));
    }

    @ExceptionHandler(value = { BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleBadRequestException(NotCreatedException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(buildErrorResponse(HttpStatus.BAD_REQUEST, ex));
    }

    private static ErrorResponse buildErrorResponse(final HttpStatus status, final RuntimeException ex) {
        return new ErrorResponse(status.value(), ex.getMessage());
    }
}
