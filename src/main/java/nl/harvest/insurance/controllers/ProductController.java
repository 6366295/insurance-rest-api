package nl.harvest.insurance.controllers;

import com.google.gson.Gson;

import nl.harvest.insurance.model.Product;
import nl.harvest.insurance.repositories.ProductRepository;

import java.lang.Iterable;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@CrossOrigin(origins = "http://frontend:8080")
public class ProductController {

    @Autowired
    private Gson gson;

    @Autowired
    private ProductRepository productRepo;

    @GetMapping()
    public ResponseEntity<String> getAllProducts() {

        Iterable<Product> products = productRepo.findAll();

        return ResponseEntity.ok(gson.toJson(products));
    }

    @PostMapping()
    public String saveProduct(@RequestBody Product newProduct) {

        // Save new product data
        productRepo.save(newProduct);

        return gson.toJson(newProduct);
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<String> getProduct(@PathVariable("productId") int productId) {

        // Find data with productId primary key
        Product product = productRepo.findById(productId).orElse(null);

        return ResponseEntity.ok(gson.toJson(product));
    }

}
