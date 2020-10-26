package gr.codehub.teamOne.repository;

import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.repository.lib.Repository;

import javax.persistence.EntityManager;

public class UserRepository extends Repository<Users, Long> {

    private EntityManager entityManager;

    public UserRepository(EntityManager entityManager){
        super(entityManager);
    }

    @Override
    public Class<Users> getEntityClass() {
        return Users.class;
    }

    @Override
    public String getEntityClassName() {
        return Users.class.getName();
    }
}
