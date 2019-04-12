package nl.harvest.insurance.controllers;

import com.google.gson.Gson;

import nl.harvest.insurance.database.Customer;
import nl.harvest.insurance.database.Product;
import nl.harvest.insurance.database.Application;
import nl.harvest.insurance.database.EntityManagerUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @GetMapping()
    public String getAllCustomers() {

        EntityManager entityManager = EntityManagerUtil.getEntityManager();

        TypedQuery<Customer> q = entityManager.createQuery("SELECT c FROM CUSTOMERS c", Customer.class);

        List<Customer> customers = q.getResultList();

        entityManager.close();

        Gson gson = new Gson();
        String jsonString = gson.toJson(customers);

        return jsonString;
    }

    @PostMapping()
    public String postCustomer(@RequestBody Customer newCustomer) {

        Gson gson = new Gson();

        EntityManager entityManager = EntityManagerUtil.getEntityManager();

        Product product1 = new Product();
        Application application = new Application(newCustomer, product1);

        entityManager.getTransaction().begin();

        entityManager.persist(newCustomer);
        entityManager.persist(product1);
        entityManager.persist(application);
        entityManager.getTransaction().commit();

        entityManager.close();

        return  gson.toJson(newCustomer);
    }

    @GetMapping(value = "/{customerId}")
    public String getCustomerResource(@PathVariable("customerId") int customerId) {

        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        Customer customer = entityManager.find(Customer.class, customerId);
        entityManager.detach(customer);

        Gson gson = new Gson();

        return gson.toJson(customer);
    }

    @GetMapping(value = "/{customerId}/products/{productId}")
    public String getProductResource(@PathVariable("customerId") String customerId, @PathVariable("productId") String productId) {

        return customerId + "/" + productId;
    }
}
