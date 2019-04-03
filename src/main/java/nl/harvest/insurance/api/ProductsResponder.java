package nl.harvest.insurance.api;

/**
 * Response for API regarding product data
 */

import nl.harvest.insurance.web.HTTPResponse;
import nl.harvest.insurance.web.HTTPRequest;

public class ProductsResponder implements Responder {

    @Override
    public HTTPResponse getMethod(HTTPRequest httpRequest) {
        HTTPResponse httpResponse = new HTTPResponse();

        httpResponse.setBody("Hello Products!");

        return httpResponse;
    }

    @Override
    public HTTPResponse postMethod(HTTPRequest httpRequest) {
        return new HTTPResponse();
    }

}
