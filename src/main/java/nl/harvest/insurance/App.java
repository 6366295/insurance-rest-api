package nl.harvest.insurance;

import nl.harvest.insurance.config.AppConfig;
import nl.harvest.insurance.model.Customer;
import nl.harvest.insurance.model.Product;
import nl.harvest.insurance.model.Application;
import nl.harvest.insurance.repositories.CustomerRepository;
import nl.harvest.insurance.repositories.ProductRepository;
import nl.harvest.insurance.repositories.ApplicationRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class App {

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);

    }

}
