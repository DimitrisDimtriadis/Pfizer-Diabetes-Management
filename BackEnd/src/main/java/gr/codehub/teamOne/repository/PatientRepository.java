package gr.codehub.teamOne.repository;

import gr.codehub.teamOne.model.Patients;
import gr.codehub.teamOne.repository.lib.IRepository;
import gr.codehub.teamOne.repository.lib.Repository;

import javax.persistence.EntityManager;

public class PatientRepository extends Repository<Patients, Long> {
    public PatientRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Patients> getEntityClass() {
        return Patients.class ;
    }

    @Override
    public String getEntityClassName() {
        return Patients.class.getName();
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
