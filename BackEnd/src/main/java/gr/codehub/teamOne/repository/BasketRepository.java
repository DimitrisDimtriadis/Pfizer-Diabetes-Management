package gr.codehub.teamOne.repository;

import gr.codehub.teamOne.model.Basket;
import gr.codehub.teamOne.repository.lib.Repository;

import javax.persistence.EntityManager;

public class BasketRepository extends Repository<Basket, Long> {

    public BasketRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class getEntityClass() {
        return Basket.class;
    }

    @Override
    public String getEntityClassName() {
        return Basket.class.getName();
    }

}
