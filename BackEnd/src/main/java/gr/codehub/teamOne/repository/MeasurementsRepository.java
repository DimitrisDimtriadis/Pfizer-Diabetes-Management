package gr.codehub.teamOne.repository;

import gr.codehub.teamOne.model.Measurement;
import gr.codehub.teamOne.repository.lib.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

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

    @Override
    public Optional<Measurement> findById(long id) {
        return super.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
