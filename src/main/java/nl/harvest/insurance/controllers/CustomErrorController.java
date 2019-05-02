package nl.harvest.insurance.controllers;

import com.google.gson.Gson;

import nl.harvest.insurance.error.ResourceNotFoundException;
import nl.harvest.insurance.error.InternalServerErrorException;
import nl.harvest.insurance.error.UnknownException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@CrossOrigin(origins = "http://frontend:8080")
public class CustomErrorController implements ErrorController {

    @Autowired
    private Gson gson;

    @RequestMapping(value = "/error", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                throw new ResourceNotFoundException();
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                throw new InternalServerErrorException();
            }
        }

        throw new UnknownException();
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}
