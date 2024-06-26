package ru.s100p.petsmongo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.s100p.petsmongo.exception.pet_exceptions.PetAlreadyExistExceptions;
import ru.s100p.petsmongo.exception.pet_exceptions.PetNotFoundException;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PetNotFoundException.class)
    protected ResponseEntity<Object> handlePetNotFoundException(Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(PetAlreadyExistExceptions.class)
    protected ResponseEntity<Object> handleOwnerAlreadyExistException(Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        ErrorResponse apiError = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage() /*"error occurred"*/);
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }
}
