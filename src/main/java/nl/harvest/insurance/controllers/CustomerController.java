package nl.harvest.insurance.controllers;

import com.google.gson.Gson;

import nl.harvest.insurance.database.Application;
import nl.harvest.insurance.database.Customer;
import nl.harvest.insurance.database.EntityManagerUtil;
import nl.harvest.insurance.database.Product;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.PersistenceContext;

import java.lang.NumberFormatException;
import java.util.List;

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

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping()
    public ResponseEntity<String> getAllCustomers() {

        // Select all fields from CUSTOMERS table
        String hql = "SELECT c FROM CUSTOMERS c";
        TypedQuery<Customer> q = entityManager.createQuery(hql, Customer.class);

        List<Customer> customers = q.getResultList();

        entityManager.close();

        // Deserialize List
        String jsonString = gson.toJson(customers);

        return ResponseEntity.ok(jsonString);

    }

    @PostMapping()
    public String saveCustomer(@RequestBody Customer newCustomer) {

        // Save new customer data
        entityManager.getTransaction().begin();
        entityManager.persist(newCustomer);
        entityManager.getTransaction().commit();
        entityManager.close();

        return gson.toJson(newCustomer);

    }

    @GetMapping(value = "/{customerId}")
    public ResponseEntity<String> getCustomer(@PathVariable("customerId") int customerId) {

        try {
            // Find data with customerId primary key
            Customer customer = entityManager.find(Customer.class, customerId);

            if (customer != null) {
                entityManager.detach(customer);

                return ResponseEntity.ok(gson.toJson(customer));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\" : 404}");
            }
        } finally {
            entityManager.close();
        }

    }

    @GetMapping(value = "/{customerId}/products")
    public ResponseEntity<String> getProductsOfCustomer(@PathVariable("customerId") String customerId) {

        String hql = "SELECT p FROM PRODUCTS p INNER JOIN p.application as a WHERE a.customer.id = :cId";

        TypedQuery<Product> q = entityManager.createQuery(hql, Product.class).setParameter("cId", Integer.parseInt(customerId));

        List<Product> products = q.getResultList();

        entityManager.close();

        // Deserialize List
        String jsonString = gson.toJson(products);

        return ResponseEntity.ok(jsonString);

    }

    @GetMapping(value = "/{customerId}/products/{productId}")
    public ResponseEntity<String> getProductOfCustomer(@PathVariable("customerId") String customerId, @PathVariable("productId") String productId) {

        String hql = "SELECT p FROM PRODUCTS p INNER JOIN p.application as a WHERE a.customer.id = :cId AND p.id = :pId";

        try {
            TypedQuery<Product> q = entityManager.createQuery(hql, Product.class)
                .setParameter("cId", Integer.parseInt(customerId))
                .setParameter("pId", Integer.parseInt(productId));

            Product product = q.getSingleResult();

            entityManager.detach(product);

            // Deserialize List
            String jsonString = gson.toJson(product);

            return ResponseEntity.ok(jsonString);
        } catch (NoResultException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"errors\":[{\"code\":404,\"message\":\"Not Found\"}]}");
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"errors\":[{\"code\":400,\"message\":\"Bad Request\"}]}");
        } finally {
            entityManager.close();
        }

    }
}
