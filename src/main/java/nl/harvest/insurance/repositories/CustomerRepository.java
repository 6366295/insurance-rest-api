package nl.harvest.insurance.repositories;

import nl.harvest.insurance.model.Customer;
import nl.harvest.insurance.model.Product;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Query("SELECT p FROM PRODUCTS p INNER JOIN p.application as a WHERE a.customer.id = :customerId")
    public Iterable<Product> findProductsByCustomerId(@Param("customerId") int customerId);

    @Query("SELECT p FROM PRODUCTS p INNER JOIN p.application as a WHERE a.customer.id = :customerId AND p.id = :productId")
    public Optional<Product> findProductByCustomerIdProductId(@Param("customerId") int customerId, @Param("productId") int productId);

}
