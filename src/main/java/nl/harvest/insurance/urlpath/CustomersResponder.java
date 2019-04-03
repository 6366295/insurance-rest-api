package nl.harvest.insurance.urlpath;

/**
 * Response for API regarding customer data
 */

import nl.harvest.insurance.web.HTTPResponse;
import nl.harvest.insurance.web.HTTPRequest;

public class CustomersResponder implements Responder {

    @Override
    public HTTPResponse getMethod(HTTPRequest httpRequest) {
        HTTPResponse httpResponse = new HTTPResponse();

        httpResponse.setProtocol("HTTP/1.1");
        httpResponse.setCode(200);
        httpResponse.setBody("Hello World!");

        return httpResponse;
    }

    @Override
    public HTTPResponse postMethod(HTTPRequest httpRequest) {
        return new HTTPResponse();
    }

}
