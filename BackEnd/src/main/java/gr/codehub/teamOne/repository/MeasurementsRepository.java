package gr.codehub.teamOne.repository;

import gr.codehub.teamOne.model.Measurements;
import gr.codehub.teamOne.repository.lib.IRepository;
import gr.codehub.teamOne.repository.lib.Repository;

import javax.persistence.EntityManager;

public class MeasurementsRepository extends Repository<Measurements, Long> {
    public MeasurementsRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Measurements> getEntityClass() {
        return Measurements.class;
    }

    @Override
    public String getEntityClassName() {
        return Measurements.class.getName();
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
