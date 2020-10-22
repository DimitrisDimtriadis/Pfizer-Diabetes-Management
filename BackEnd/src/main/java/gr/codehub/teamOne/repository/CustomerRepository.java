package gr.codehub.teamOne.repository;

import gr.codehub.teamOne.model.Customer;
import gr.codehub.teamOne.repository.lib.Repository;

import javax.persistence.EntityManager;

public class CustomerRepository extends Repository<Customer, Long> {

    public CustomerRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Customer> getEntityClass() {
        return Customer.class;
    }

    @Override
    public String getEntityClassName() {
        return Customer.class.getName();
    }
}
