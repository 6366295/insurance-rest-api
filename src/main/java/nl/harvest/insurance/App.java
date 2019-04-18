package nl.harvest.insurance;

import nl.harvest.insurance.model.Customer;
import nl.harvest.insurance.model.Product;
import nl.harvest.insurance.model.Application;
import nl.harvest.insurance.repositories.CustomerRepository;
import nl.harvest.insurance.repositories.ProductRepository;
import nl.harvest.insurance.repositories.ApplicationRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class App {

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Test data
        CustomerRepository repo1 = context.getBean(CustomerRepository.class);
        ProductRepository repo2 = context.getBean(ProductRepository.class);
        ApplicationRepository repo3 = context.getBean(ApplicationRepository.class);
        Customer customer = new Customer("test", "test2");
        Customer customer2 = new Customer("test3", "test4");
        Product product = new Product();
        Application app = new Application(customer, product);
        Application app2 = new Application(customer2, product);
        repo1.save(customer);
        repo1.save(customer2);
        repo2.save(product);
        repo3.save(app);
        repo3.save(app2);
    }

}
