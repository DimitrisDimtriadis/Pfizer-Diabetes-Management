package gr.codehub.teamOne.repository;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.model.Measurement;
import gr.codehub.teamOne.repository.lib.Repository;
import gr.codehub.teamOne.representation.MeasurementsSearchParamDTO;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class MeasurementRepository extends Repository<Measurement, Long> {

    private EntityManager entityManager;

    public MeasurementRepository(EntityManager entityManager) {
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

    /**
     * Method to search specific measurements in base, based on user id.
     * @param paramDTO Object from MeasurementsSearchParamDTO
     * @return a list with measurements .
     * @throw new NotFoundException("Not found measurements")
     */
    public List getSpecificMeasurements(MeasurementsSearchParamDTO paramDTO) throws BadEntityException, NotFoundException {

        if (paramDTO == null) throw new BadEntityException("Wrong input model");

        Query baseQuery;
        boolean hasID = (paramDTO.getUserID() != null);
        boolean hasStart = (paramDTO.getStartAt() != null);
        boolean hasEnd = (paramDTO.getEndAt() != null);

        if(hasID && hasStart && hasEnd){
            baseQuery = entityManager.createQuery("from Measurement m where user_id = :userid and measurementDate > :startAt and measurementDate < :endAt")
                    .setParameter("userid", paramDTO.getUserID())
                    .setParameter("startAt", paramDTO.getStartAt())
                    .setParameter("endAt", paramDTO.getEndAt());

        } else if(hasID && hasStart) {
            baseQuery = entityManager.createQuery("from Measurement m where user_id = :userid and measurementDate > :startAt")
                    .setParameter("userid", paramDTO.getUserID())
                    .setParameter("startAt", paramDTO.getStartAt());
        } else if(hasStart && hasEnd){
            baseQuery = entityManager.createQuery("from Measurement m where measurementDate > :startAt and measurementDate < :endAt")
                    .setParameter("startAt", paramDTO.getStartAt())
                    .setParameter("endAt", paramDTO.getEndAt());
        } else if(hasID && hasEnd){
            baseQuery = entityManager.createQuery("from Measurement m where user_id = :userid and measurementDate < :endAt")
                    .setParameter("userid", paramDTO.getUserID())
                    .setParameter("endAt", paramDTO.getEndAt());
        } else if(hasEnd){
            baseQuery = entityManager.createQuery("from Measurement m where measurementDate < :endAt")
                    .setParameter("endAt", paramDTO.getEndAt());
        } else if(hasStart){
            baseQuery = entityManager.createQuery("from Measurement m where measurementDate > :startAt")
                    .setParameter("startAt", paramDTO.getStartAt());
        } else if(hasID){
            baseQuery = entityManager.createQuery("from Measurement m where user_id = :userid")
                    .setParameter("userid", paramDTO.getUserID());
        } else {
            baseQuery = entityManager.createQuery("from Measurement");
        }

        List listWithMeasurements = baseQuery.getResultList();
        return listWithMeasurements;
    }
}