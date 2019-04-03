package nl.harvest.insurance.web;

/***
 * HTTP Response object
 */

import java.util.HashMap;
import java.util.Map;

public class HTTPResponse {

    private Map<String, String> headers = new HashMap<String, String>();

    private static Map<Integer, String> codes = Map.of(
        200, "200 OK",
        404, "404 NOT FOUND"
    );

    private String protocol = null;
    private String body = null;
    private int code = 0;

    public HTTPResponse() {
    }

    // Add Headers
    public String toString() {

        return this.protocol + " " + getCode() + "\r\n\r\n" + this.body + "\n";

    }

    public String getCode() {

        return codes.get(this.code);

    }

    public String getProtocol() {

        return this.protocol;

    }

    public String getBody() {

        return this.body;

    }

    public String getHeader(String name) {

        return name + ": " + headers.get(name);

    }

    public void setCode(int code) {

        this.code = code;

    }

    public void setProtocol(String protocol) {

        this.protocol = protocol;

    }

    public void setBody(String body) {

        this.body = body;

    }

    public void addHeader(String name, String value) {

        headers.put(name, value);

    }

}
