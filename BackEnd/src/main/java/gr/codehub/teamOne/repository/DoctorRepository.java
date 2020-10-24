package gr.codehub.teamOne.repository;

import gr.codehub.teamOne.model.Doctors;
import gr.codehub.teamOne.repository.lib.IRepository;
import gr.codehub.teamOne.repository.lib.Repository;

import javax.persistence.EntityManager;

public class DoctorRepository extends Repository implements IRepository {
    public DoctorRepository(EntityManager entityManager) {
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
