package nl.harvest.insurance.web;

/***
 * HTTP Request object
 */

import java.util.HashMap;
import java.util.Map;

public class HTTPRequest {

    private Map<String, String> headers = new HashMap<String, String>();

    private String method = null;
    private String path = null;
    private String protocol = null;

    public HTTPRequest(String request) {

        String[] requestFields = request.split("\r\n");
        String[] requestHeader = requestFields[0].split(" ");

        method = requestHeader[0];
        path = requestHeader[1];
        protocol = requestHeader[2];

        for(String field : requestFields) {
            String[] splitField = field.split(": ", 2);

            if(splitField.length > 1) {
                headers.put(splitField[0], splitField[1]);
            }
        }

    }

    public String getMethod() {

        return this.method;

    }

    public String getPath() {

        return this.path;

    }

    public String getProtocol() {

        return this.protocol;

    }

    public String getHeader(String name) {

        return headers.get(name);

    }

}
