package gr.codehub.teamOne.repository;

import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.repository.lib.Repository;
import gr.codehub.teamOne.representation.LoginCredentialDTO;
import gr.codehub.teamOne.representation.UsersDTO;
import gr.codehub.teamOne.security.AccessRole;

import javax.persistence.EntityManager;
import java.util.List;

public class UserRepository extends Repository<Users, Long> {

    private EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<Users> getEntityClass() {
        return Users.class;
    }

    @Override
    public String getEntityClassName() {
        return Users.class.getName();
    }

    /**
     * Function to check if user exist on base before saving
     *
     * @param usersDTO Object of user that want to save on base
     * @return if exist other entry return true
     */
    public boolean checkIfAccountExist(UsersDTO usersDTO) {

        List userList = entityManager.createQuery("from Users u where u.email = :email or u.amka = :amka")
                .setParameter("email", usersDTO.getEmail())
                .setParameter("amka", usersDTO.getAmka())
                .getResultList();

        return userList.size() > 0;
    }

    public List findUserWithCredential(LoginCredentialDTO loginCredentialDTO) {
        return entityManager.createQuery("from Users u where u.email = :email or u.password = :password")
                .setParameter("email", loginCredentialDTO.getUserEmail())
                .setParameter("password", loginCredentialDTO.getUserPassword())
                .getResultList();
    }

    public List getAllUsersBasedOnRole(AccessRole accessRole) {

        return entityManager.createQuery("from Users u where u.accountType = :accessRole")
                .setParameter("accessRole", accessRole)
                .getResultList();
    }

}