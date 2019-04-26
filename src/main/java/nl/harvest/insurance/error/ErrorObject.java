package nl.harvest.insurance.error;

import java.util.LinkedHashMap;
import java.util.Map;

public class ErrorObject {

    private Map<String, String> error;

    public ErrorObject() {
        error = new LinkedHashMap<String, String>();

        error.put("code", "");
        error.put("message", "");
        error.put("path", "");
    }

    public void setCode(int code) {
        error.put("code", String.valueOf(code));
    }

    public void setMessage(String message) {
        error.put("message", message);
    }

    public void setPath(String path) {
        error.put("path", path);
    }

}
