package gr.codehub.teamOne.repository;

import gr.codehub.teamOne.model.Doctors;
import gr.codehub.teamOne.repository.lib.Repository;

import javax.persistence.EntityManager;

public class ProductRepository extends Repository<Doctors, Long> {

    public ProductRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class getEntityClass() {
        return Doctors.class;
    }

    @Override
    public String getEntityClassName() {
        return Doctors.class.getName();
    }
}
