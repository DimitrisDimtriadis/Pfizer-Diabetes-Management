package gr.codehub.teamOne.repository;

import gr.codehub.teamOne.model.Measurements;
import gr.codehub.teamOne.repository.lib.Repository;

import javax.persistence.EntityManager;

public class BasketRepository extends Repository<Measurements, Long> {

    public BasketRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class getEntityClass() {
        return Measurements.class;
    }

    @Override
    public String getEntityClassName() {
        return Measurements.class.getName();
    }
}
