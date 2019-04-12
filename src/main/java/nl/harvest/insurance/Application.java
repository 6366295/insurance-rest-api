package nl.harvest.insurance;

import nl.harvest.insurance.database.EntityManagerUtil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        EntityManagerUtil.createEntityManagerFactory();

        SpringApplication.run(Application.class, args);
    }
}
