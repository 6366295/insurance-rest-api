package nl.harvest.insurance.api;

/**
 * HashMap of all suported API call paths
 */

import java.util.HashMap;
import java.util.Map;

public final class ResponderMap {

    Map<String, Responder> map = new HashMap<String, Responder>();

    public ResponderMap() {
        // Add more paths here
        map.put("/customers", new CustomersResponder());
        map.put("/products", new ProductsResponder());
    }

    public Responder get(String path) {
        return map.get(path);
    }

}
