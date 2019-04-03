package nl.harvest.insurance.web;

/***
 * HTTP Request object
 */

import java.util.HashMap;
import java.util.Map;

public class HTTPRequest {

    private Map<String, String> headers = new HashMap<String, String>();
    private Map<String, String> parameters = new HashMap<String, String>();

    private String method = null;
    private String path = null;
    private String protocol = null;

    public HTTPRequest(String request) {

        String[] requestFields = request.split("\r\n");
        String[] requestHeader = requestFields[0].split(" ");

        method = requestHeader[0];
        protocol = requestHeader[2];

        // Split path and query
        String[] pathQuery = requestHeader[1].split("\\?", 2);

        path = pathQuery[0];

        // Split query into parameters, and add them into a map
        if (pathQuery.length == 2) {
            String[] query = pathQuery[1].split("&");

            for (String keyvalue : query) {
                String[] keyvalue2 = keyvalue.split("=", 2);

                parameters.put(keyvalue2[0], keyvalue2[1]);
            }
        }

        // Split each header field, and add them into a map
        for (String field : requestFields) {
            String[] splitField = field.split(": ", 2);

            if (splitField.length > 1) {
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

    public String getParameter(String name) {

        return parameters.get(name);

    }

}
