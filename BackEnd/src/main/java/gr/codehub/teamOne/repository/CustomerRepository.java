package gr.codehub.teamOne.repository;

import gr.codehub.teamOne.model.Customer;
import gr.codehub.teamOne.repository.lib.Repository;

import javax.persistence.EntityManager;
import java.util.List;

public class CustomerRepository extends Repository<Customer, Long> {

    private EntityManager entityManager;

    public CustomerRepository(EntityManager entityManager) {

        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<Customer> getEntityClass() {
        return Customer.class;
    }

    @Override
    public String getEntityClassName() {
        return Customer.class.getName();
    }

    //JBQL
    public List<Customer> findByAddress(String address) {
        List<Customer> cs = entityManager.createQuery("from Customer c WHERE c.address = :address ")
                .setParameter("address", address)
                .getResultList();
        return cs;
    }
}
