package nl.harvest.insurance.error;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ErrorObject {

    private String code;
    private String message;
    private String path;

    private Map<String, ArrayList<String>> details;

    public ErrorObject() {
        details = new HashMap<String, ArrayList<String>>();
    }

    public void setCode(int code) {
        this.code = String.valueOf(code);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void addDetail(String variable, String violation) {
        if (details.containsKey(variable)) {
            details.get(variable).add(violation);
        } else {
            ArrayList<String> violations = new ArrayList<String>();
            violations.add(violation);

            details.put(variable, violations);
        }
    }

}
