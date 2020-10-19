package gr.codehub.teamOne.repository;

import gr.codehub.teamOne.repository.lib.Repository;

import javax.persistence.EntityManager;

public class BasketRepository extends Repository {

    public BasketRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class getEntityClass() {
        return this.getClass();
    }

    @Override
    public String getEntityClassName() {
        return this.getEntityClass().getName();
    }
}
