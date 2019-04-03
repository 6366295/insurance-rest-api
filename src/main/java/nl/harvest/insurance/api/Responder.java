package nl.harvest.insurance.api;

/**
 * Interface for API responses
 */

import nl.harvest.insurance.web.HTTPResponse;
import nl.harvest.insurance.web.HTTPRequest;

public interface Responder {

    public HTTPResponse getMethod(HTTPRequest httpRequest);
    public HTTPResponse postMethod(HTTPRequest httpRequest);

}
