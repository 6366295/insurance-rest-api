package nl.harvest.insurance.controllers;

import com.google.gson.Gson;

import nl.harvest.insurance.database.Application;
import nl.harvest.insurance.database.Customer;
import nl.harvest.insurance.database.EntityManagerUtil;
import nl.harvest.insurance.database.Product;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private static Gson gson = new Gson();

    @GetMapping()
    public String getAllCustomers() {

        EntityManager entityManager = EntityManagerUtil.getEntityManager();

        // Select all fields from CUSTOMERS table
        String hql = "SELECT c FROM CUSTOMERS c";
        TypedQuery<Customer> q = entityManager.createQuery(hql, Customer.class);

        List<Customer> customers = q.getResultList();

        entityManager.close();

        // Deserialize List
        String jsonString = gson.toJson(customers);

        return jsonString;

    }

    @PostMapping()
    public String saveCustomer(@RequestBody Customer newCustomer) {

        EntityManager entityManager = EntityManagerUtil.getEntityManager();

        // Save new customer data
        entityManager.getTransaction().begin();
        entityManager.persist(newCustomer);
        entityManager.getTransaction().commit();
        entityManager.close();

        return gson.toJson(newCustomer);

    }

    @GetMapping(value = "/{customerId}")
    public String getCustomer(@PathVariable("customerId") int customerId) {

        // Find data with customerId primary key
        EntityManager entityManager = EntityManagerUtil.getEntityManager();

        Customer customer = entityManager.find(Customer.class, customerId);

        entityManager.detach(customer);
        entityManager.close();

        return gson.toJson(customer);

    }

    @GetMapping(value = "/{customerId}/products")
    public String getProductsOfCustomer(@PathVariable("customerId") String customerId) {

        EntityManager entityManager = EntityManagerUtil.getEntityManager();

        String hql = "SELECT p FROM PRODUCTS p INNER JOIN p.application as a WHERE a.customer = " + customerId;

        TypedQuery<Product> q = entityManager.createQuery(hql, Product.class);

        List<Product> products = q.getResultList();

        entityManager.close();

        // Deserialize List
        String jsonString = gson.toJson(products);

        return jsonString;

    }

    @GetMapping(value = "/{customerId}/products/{productId}")
    public String getProductOfCustomer(@PathVariable("customerId") String customerId, @PathVariable("productId") String productId) {

        EntityManager entityManager = EntityManagerUtil.getEntityManager();

        String hql = "SELECT p FROM PRODUCTS p INNER JOIN p.application as a WHERE a.customer = " + customerId + " AND p.id = " + productId;

        TypedQuery<Product> q = entityManager.createQuery(hql, Product.class);

        List<Product> products = q.getResultList();

        entityManager.close();

        // Deserialize List
        String jsonString = gson.toJson(products);

        return jsonString;

    }
}
