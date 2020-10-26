package gr.codehub.teamOne.repository;

import gr.codehub.teamOne.model.Product;
import gr.codehub.teamOne.repository.lib.Repository;

import javax.persistence.EntityManager;

public class ProductRepository extends Repository<Product, Long> {

    public ProductRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class getEntityClass() {
        return Product.class;
    }

    @Override
    public String getEntityClassName() {
        return Product.class.getName();
    }

}
