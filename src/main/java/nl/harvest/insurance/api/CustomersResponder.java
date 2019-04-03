package nl.harvest.insurance.api;

/**
 * Response for API regarding customer data
 */

import nl.harvest.insurance.web.HTTPResponse;
import nl.harvest.insurance.web.HTTPRequest;

public class CustomersResponder implements Responder {

    @Override
    public HTTPResponse getMethod(HTTPRequest httpRequest) {
        HTTPResponse httpResponse = new HTTPResponse();

        httpResponse.setBody("Hello Customers!");

        return httpResponse;
    }

    @Override
    public HTTPResponse postMethod(HTTPRequest httpRequest) {
        return new HTTPResponse();
    }

}