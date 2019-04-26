package nl.harvest.insurance.error;

import com.google.gson.Gson;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

    @Autowired
    private Gson gson;

    private HttpHeaders header = new HttpHeaders();

    public ExceptionHandlers() {
        super();

        header.setContentType(MediaType.APPLICATION_JSON_UTF8);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleBadRequest(final MethodArgumentTypeMismatchException ex, final WebRequest request) {
        ErrorObject response = new ErrorObject();

        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("ID should be a number");
        response.setPath(((ServletWebRequest) request).getRequest().getRequestURI().toString());

        return handleExceptionInternal(ex, gson.toJson(response), header, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleBadRequest(final ResourceNotFoundException ex, final WebRequest request) {
        ErrorObject response = new ErrorObject();

        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setMessage("Resource not found");
        response.setPath(((ServletWebRequest) request).getRequest().getRequestURI().toString());

        return handleExceptionInternal(ex, gson.toJson(response), header, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({InternalServerErrorException.class})
    public ResponseEntity<Object> handleBadRequest(final InternalServerErrorException ex, final WebRequest request) {
        ErrorObject response = new ErrorObject();

        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage("Internal Server Error");
        response.setPath(((ServletWebRequest) request).getRequest().getRequestURI().toString());

        return handleExceptionInternal(ex, gson.toJson(response), header, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex, final WebRequest request) {
        ErrorObject response = new ErrorObject();

        response.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        response.setMessage("Invalid input");
        response.setPath(((ServletWebRequest) request).getRequest().getRequestURI().toString());

        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

        for (ConstraintViolation<?> violation:violations) {
            System.out.println(violation.getMessage());
        }

        return handleExceptionInternal(ex, gson.toJson(response), header, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

}
