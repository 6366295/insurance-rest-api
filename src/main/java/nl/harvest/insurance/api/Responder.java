package nl.harvest.insurance.api;

/**
 * Interface for API responses
 *
 * Default protocol is HTTP/1.1 and default response code is 200 OK
 */

import nl.harvest.insurance.web.HTTPResponse;
import nl.harvest.insurance.web.HTTPRequest;

public interface Responder {

    public HTTPResponse getMethod(HTTPRequest httpRequest);
    public HTTPResponse postMethod(HTTPRequest httpRequest);

}
