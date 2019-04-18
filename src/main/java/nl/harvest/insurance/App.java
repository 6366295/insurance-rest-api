package nl.harvest.insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class App {

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Fill database temporarily with test customer and product
        // Customer customer = new Customer();
        // Customer customer2 = new Customer();
        // Product product = new Product();
        // Product product2 = new Product();
        // Product product3 = new Product();
        //
        // Application application = new Application(customer, product);
        // Application application2 = new Application(customer, product2);
        // Application application3 = new Application(customer2, product3);
        //
        // entityManager.getTransaction().begin();
        //
        // entityManager.persist(customer);
        // entityManager.persist(customer2);
        // entityManager.persist(product);
        // entityManager.persist(product2);
        // entityManager.persist(product3);
        // entityManager.persist(application);
        // entityManager.persist(application2);
        // entityManager.persist(application3);
        // entityManager.getTransaction().commit();
        // entityManager.close();
    }

}
