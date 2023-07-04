package ru.isands.BackendTask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse("Error 404 entity not found: " + ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleUnknownSortTypeException(UnknownSortTypeException ex) {
        return new ResponseEntity<>(new ErrorResponse("Error 400 bad request: " + ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleConflictException(ConflictException ex) {
        return new ResponseEntity<>(new ErrorResponse("Error 409 conflict: " + ex.getMessage()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleConflictException(EntityNotValidException ex) {
        return new ResponseEntity<>(new ErrorResponse("Error 400 entity not valid: " + ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
