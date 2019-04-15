package nl.harvest.insurance.controllers;

import com.google.gson.Gson;

import nl.harvest.insurance.database.Application;
import nl.harvest.insurance.database.Customer;
import nl.harvest.insurance.database.EntityManagerUtil;
import nl.harvest.insurance.database.Product;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

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
@CrossOrigin(origins = "http://localhost:8080")
public class ProductController {

    private static Gson gson = new Gson();

    @GetMapping()
    public ResponseEntity<String> getAllProducts() {

        EntityManager entityManager = EntityManagerUtil.getEntityManager();

        String hql = "SELECT p FROM PRODUCTS p";

        // Select all fields from PRODUCTS table
        TypedQuery<Product> q = entityManager.createQuery(hql, Product.class);

        List<Product> products = q.getResultList();

        entityManager.close();

        // Deserialize List
        String jsonString = gson.toJson(products);

        return ResponseEntity.ok(jsonString);

    }

    @PostMapping()
    public String saveProduct(@RequestBody Product newProduct) {

        EntityManager entityManager = EntityManagerUtil.getEntityManager();

        // Save new product data
        entityManager.getTransaction().begin();
        entityManager.persist(newProduct);
        entityManager.getTransaction().commit();
        entityManager.close();

        return gson.toJson(newProduct);

    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<String> getProduct(@PathVariable("productId") int productId) {

        // Find data with productId primary key
        EntityManager entityManager = EntityManagerUtil.getEntityManager();

        Product product = entityManager.find(Product.class, productId);

        entityManager.detach(product);
        entityManager.close();

        return ResponseEntity.ok(gson.toJson(product));
        // return gson.toJson(product);

    }

}
