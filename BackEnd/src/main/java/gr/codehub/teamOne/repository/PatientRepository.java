package gr.codehub.teamOne.repository;

import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.repository.lib.Repository;
import gr.codehub.teamOne.representation.LoginCredentialDTO;
import gr.codehub.teamOne.representation.UsersDTO;

import javax.persistence.EntityManager;
import java.util.List;

public class PatientRepository extends Repository<Users, Long> {

    private EntityManager entityManager;


    public PatientRepository(EntityManager entityManager) {
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


    public boolean checkIfIsPatient(UsersDTO usersDTO) {

        List patientList = entityManager.createQuery("from Users u where u.accountType = 3")
                .setParameter("email", usersDTO.getEmail())
                .setParameter("amka", usersDTO.getAmka())
                .getResultList();

        return patientList.size() > 0;
    }


}
