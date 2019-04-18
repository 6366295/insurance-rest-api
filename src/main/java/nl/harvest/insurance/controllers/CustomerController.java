package nl.harvest.insurance.controllers;

import com.google.gson.Gson;

import nl.harvest.insurance.model.Customer;
import nl.harvest.insurance.model.Product;
import nl.harvest.insurance.repositories.CustomerRepository;

import java.lang.Iterable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@CrossOrigin(origins = "http://localhost:8080")
public class CustomerController {

    private static Gson gson = new Gson();

    @Autowired
    private CustomerRepository customerRepo;

    @GetMapping()
    public ResponseEntity<String> getAllCustomers() {

        Iterable<Customer> customers = customerRepo.findAll();

        return ResponseEntity.ok(gson.toJson(customers));
    }

    @PostMapping()
    public String saveCustomer(@RequestBody Customer newCustomer) {

        // Save new customer data
        customerRepo.save(newCustomer);

        return gson.toJson(newCustomer);
    }

    @GetMapping(value = "/{customerId}")
    public ResponseEntity<String> getCustomer(@PathVariable("customerId") int customerId) {

        // Find data with customerId primary key
        Customer customer = customerRepo.findById(customerId).orElse(null);

        return ResponseEntity.ok(gson.toJson(customer));
    }

    @GetMapping(value = "/{customerId}/products")
    public ResponseEntity<String> getProductsOfCustomer(@PathVariable("customerId") int customerId) {

        Iterable<Product> products = customerRepo.findProductsByCustomerId(customerId);

        return ResponseEntity.ok(gson.toJson(products));
    }

    @GetMapping(value = "/{customerId}/products/{productId}")
    public ResponseEntity<String> getProductOfCustomer(@PathVariable("customerId") int customerId, @PathVariable("productId") int productId) {

        Product product = customerRepo.findProductByCustomerIdProductId(customerId, productId);

        return ResponseEntity.ok(gson.toJson(product));
    }
}
