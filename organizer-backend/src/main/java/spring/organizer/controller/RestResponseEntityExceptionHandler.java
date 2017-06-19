package spring.organizer.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import spring.organizer.errorhandler.EntityValidationException;
import spring.organizer.errorhandler.ResourceNotFoundException;
import spring.organizer.errorhandler.ClientErrorInformation;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {  ResourceNotFoundException.class })
    protected ResponseEntity<Object> handleResourceNotFoundExceptionConflict(RuntimeException ex, WebRequest request) {
        List<String> details= new ArrayList<>();
        details.add("The requested "+ex.getMessage()+ " was not found!");
        ClientErrorInformation error = new ClientErrorInformation(ex.getMessage(),HttpStatus.NOT_FOUND,details ,  request.getDescription(false));
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {  EntityValidationException.class })
    protected ResponseEntity<Object> handleEntityValidationExceptionConflict(RuntimeException ex, WebRequest request) {
        List<String> details= ((EntityValidationException)ex).getValidationErrors();
        ClientErrorInformation error = new ClientErrorInformation(ex.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY, details,  request.getDescription(false));
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}