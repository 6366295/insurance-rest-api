package nl.harvest.insurance.controllers;

import com.google.gson.Gson;

import nl.harvest.insurance.error.ResourceNotFound;
import nl.harvest.insurance.error.ErrorObject;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    @Autowired
    private Gson gson;

    private HttpHeaders header = new HttpHeaders();

    public ErrorController() {
        super();

        header.setContentType(MediaType.APPLICATION_JSON_UTF8);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleBadRequest(final MethodArgumentTypeMismatchException ex, final WebRequest request) {
        final String bodyOfResponse = "ID should be a number";

        return handleExceptionInternal(ex, gson.toJson(new ErrorObject()), header, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ResourceNotFound.class})
    public ResponseEntity<Object> handleBadRequest(final ResourceNotFound ex, final WebRequest request) {
        final String bodyOfResponse = "Resource not found";

        return handleExceptionInternal(ex, bodyOfResponse, header, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex, final WebRequest request) {
        final String bodyOfResponse = "Invalid Input";
        System.out.println(ex);

        return handleExceptionInternal(ex, bodyOfResponse, header, HttpStatus.NOT_FOUND, request);
    }

}
