package nl.harvest.insurance.repositories;

import nl.harvest.insurance.model.Application;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Integer> {
}
