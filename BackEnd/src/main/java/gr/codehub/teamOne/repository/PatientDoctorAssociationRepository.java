package gr.codehub.teamOne.repository;

import gr.codehub.teamOne.model.PatientDoctorAssociation;
import gr.codehub.teamOne.repository.lib.Repository;

import javax.persistence.EntityManager;

public class PatientDoctorAssociationRepository extends Repository<gr.codehub.teamOne.model.PatientDoctorAssociation, Long> {

    private EntityManager entityManager;

    public PatientDoctorAssociationRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<PatientDoctorAssociation> getEntityClass() {
        return PatientDoctorAssociation.class;
    }

    @Override
    public String getEntityClassName() {
        return PatientDoctorAssociation.class.getName();
    }
}
