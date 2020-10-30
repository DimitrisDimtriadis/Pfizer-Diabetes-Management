package gr.codehub.teamOne.repository;

import gr.codehub.teamOne.model.Measurement;
import gr.codehub.teamOne.repository.lib.Repository;

import javax.persistence.EntityManager;

public class MeasurementsRepository extends Repository<Measurement, Long> {

    private EntityManager entityManager;

    public MeasurementsRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<Measurement> getEntityClass() {
        return Measurement.class;
     }

    @Override
    public String getEntityClassName() {
        return Measurement.class.getName();
    }
}