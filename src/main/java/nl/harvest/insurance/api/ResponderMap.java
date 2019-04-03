package nl.harvest.insurance.api;

/**
 * HashMap of all suported API call paths
 */

import java.util.HashMap;

public final class ResponderMap extends HashMap<String, Responder> {

    public ResponderMap() {
        // Add more paths here
        this.put("/customers", new CustomersResponder());
        this.put("/products", new ProductsResponder());
    }

}
