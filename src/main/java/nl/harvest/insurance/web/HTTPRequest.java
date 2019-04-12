package nl.harvest.insurance.web;

/***
 * HTTP Request object
 */

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class HTTPRequest {

    private Map<String, String> headers = new HashMap<String, String>();
    private Map<String, String> parameters = new HashMap<String, String>();

    private String method = null;
    private String path = null;
    private String protocol = null;
    private String body = null;

    public HTTPRequest(String request) {

        if (request != null) {
            String[] requestFields = request.split("\r\n");
            String[] requestHeader = requestFields[0].split(" ");

            this.method = requestHeader[0];
            this.protocol = requestHeader[2];

            // Split path and query
            String[] pathQuery = requestHeader[1].split("\\?", 2);

            // TODO: splitpath for ID and other paths
            this.path = pathQuery[0];

            // Split query into parameters, and add them into a map
            if (pathQuery.length == 2) {
                String[] query = pathQuery[1].split("&");

                for (String keyvalue : query) {
                    String[] keyvalue2 = keyvalue.split("=", 2);

                    this.parameters.put(keyvalue2[0], keyvalue2[1]);
                }
            }

            // Split each header field, and add them into a map
            for (String field : requestFields) {
                String[] splitField = field.split(": ", 2);

                if (splitField.length > 1) {
                    this.headers.put(splitField[0], splitField[1]);
                }
            }
        }

        Pattern pattern = Pattern.compile("(\\{.*?\\})");
        Matcher matcher = pattern.matcher(request);

        if (matcher.find()) {
            this.body = matcher.group(1);
        }

    }

    public String getMethod() {

        return this.method;

    }

    public String getPath() {

        return this.path;

    }

    public String getBody() {

        return this.body;

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
