package nl.harvest.insurance.controllers;

import com.google.gson.Gson;

import nl.harvest.insurance.model.Customer;
import nl.harvest.insurance.model.Product;
import nl.harvest.insurance.repositories.CustomerRepository;
import nl.harvest.insurance.error.ResourceNotFoundException;

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
@CrossOrigin(origins = "http://frontend:8080")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private Gson gson;

    @GetMapping()
    public ResponseEntity<String> getAllCustomers() {
        Iterable<Customer> customers = customerRepo.findAll();

        return ResponseEntity.ok(gson.toJson(customers));
    }

    @PostMapping()
    public String saveCustomer(@RequestBody Customer newCustomer) {
        customerRepo.save(newCustomer);

        return gson.toJson(newCustomer);
    }

    @GetMapping(value = "/{customerId}")
    public ResponseEntity<String> getCustomer(@PathVariable("customerId") int customerId) {
        Customer customer = customerRepo.findById(customerId).orElseThrow(ResourceNotFoundException::new);

        return ResponseEntity.ok(gson.toJson(customer));
    }

    @GetMapping(value = "/{customerId}/products")
    public ResponseEntity<String> getProductsOfCustomer(@PathVariable("customerId") int customerId) {
        Iterable<Product> products = customerRepo.findProductsByCustomerId(customerId);

        return ResponseEntity.ok(gson.toJson(products));
    }

    @GetMapping(value = "/{customerId}/products/{productId}")
    public ResponseEntity<String> getProductOfCustomer(@PathVariable("customerId") int customerId, @PathVariable("productId") int productId) {
        Product product = customerRepo.findProductByCustomerIdProductId(customerId, productId).orElseThrow(ResourceNotFoundException::new);

        return ResponseEntity.ok(gson.toJson(product));
    }
}
