package nl.harvest.insurance.error;

import java.util.LinkedHashMap;
import java.util.Map;

public class ErrorObject {

    private Map<String, String> error;

    public ErrorObject() {
        error = new LinkedHashMap();

        error.put("code", "404");
        error.put("message", "Resource Not Found");
        error.put("path", "path");
    }

}
