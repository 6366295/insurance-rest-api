package nl.harvest.insurance.controllers;

import nl.harvest.insurance.error.ResourceNotFound;
import nl.harvest.insurance.error.ErrorObject;

import com.google.gson.Gson;

import javax.persistence.EntityNotFoundException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.MediaType;

import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    private Gson gson = new Gson();
    private HttpHeaders header = new HttpHeaders();

    public ErrorController() {
        super();

        header.setContentType(MediaType.APPLICATION_JSON_UTF8);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleBadRequest(final MethodArgumentTypeMismatchException ex, final WebRequest request) {
        final String bodyOfResponse = "ID should be a number";
        System.out.println(ex);

        return handleExceptionInternal(ex, gson.toJson(new ErrorObject()), header, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ResourceNotFound.class})
    public ResponseEntity<Object> handleBadRequest(final ResourceNotFound ex, final WebRequest request) {
        final String bodyOfResponse = "Resource not found";

        return handleExceptionInternal(ex, bodyOfResponse, header, HttpStatus.NOT_FOUND, request);
    }

}
