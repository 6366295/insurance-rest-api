package nl.harvest.insurance;

import nl.harvest.insurance.model.Customer;
import nl.harvest.insurance.repositories.CustomerRepository;
import nl.harvest.insurance.error.ResourceNotFoundException;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerControllerTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepo;

    @Test
    public void findCustomerById() {

        Customer customer = new Customer("Trieu", "M");
        entityManager.persist(customer);
        entityManager.flush();

        Customer found = customerRepo.findById(1).orElseThrow(ResourceNotFoundException::new);;

        assertThat(found.getSurname()).isEqualTo(customer.getSurname());

        Customer falseCustomer = new Customer("Trieu5", "M");
        assertThrows(ConstraintViolationException.class, () -> {entityManager.persist(falseCustomer); entityManager.flush();});

        Customer blankCustomer = new Customer();
        assertThrows(ConstraintViolationException.class, () -> {entityManager.persist(blankCustomer); entityManager.flush();});

    }

}
