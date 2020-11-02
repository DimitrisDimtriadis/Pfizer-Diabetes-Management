package gr.codehub.teamOne.repository;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.model.Measurement;
import gr.codehub.teamOne.repository.lib.Repository;
import gr.codehub.teamOne.representation.DataAvgDTO;
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
            baseQuery = entityManager.createQuery("from Measurement where user_id = :userid and measurementDate > :startAt and measurementDate < :endAt")
                    .setParameter("userid", paramDTO.getUserID())
                    .setParameter("startAt", paramDTO.getStartAt())
                    .setParameter("endAt", paramDTO.getEndAt());

        } else if(hasID && hasStart) {
            baseQuery = entityManager.createQuery("from Measurement where user_id = :userid and measurementDate > :startAt")
                    .setParameter("userid", paramDTO.getUserID())
                    .setParameter("startAt", paramDTO.getStartAt());
        } else if(hasStart && hasEnd){
            baseQuery = entityManager.createQuery("from Measurement where measurementDate > :startAt and measurementDate < :endAt")
                    .setParameter("startAt", paramDTO.getStartAt())
                    .setParameter("endAt", paramDTO.getEndAt());
        } else if(hasID && hasEnd){
            baseQuery = entityManager.createQuery("from Measurement where user_id = :userid and measurementDate < :endAt")
                    .setParameter("userid", paramDTO.getUserID())
                    .setParameter("endAt", paramDTO.getEndAt());
        } else if(hasEnd){
            baseQuery = entityManager.createQuery("from Measurement where measurementDate < :endAt")
                    .setParameter("endAt", paramDTO.getEndAt());
        } else if(hasStart){
            baseQuery = entityManager.createQuery("from Measurement where measurementDate > :startAt")
                    .setParameter("startAt", paramDTO.getStartAt());
        } else if(hasID){
            baseQuery = entityManager.createQuery("from Measurement where user_id = :userid")
                    .setParameter("userid", paramDTO.getUserID());
        } else {
            baseQuery = entityManager.createQuery("from Measurement");
        }

        List listWithMeasurements = baseQuery.getResultList();
        return listWithMeasurements;
    }

    public DataAvgDTO calculateAvgOfData(MeasurementsSearchParamDTO searchParamDTO){

        String query = "select avg(carbIntake), avg(bloodGlucoseLevel), count(*) from Measurement ";
        List rowsWithMeasurements;

        if(searchParamDTO.getUserID() != null){
            query += "where user_id = :userID ";
        }

        if(searchParamDTO.getStartAt() != null && searchParamDTO.getEndAt() != null){
            query += "where measurementDate > :startAt and measurementDate < :endAt";
            rowsWithMeasurements = entityManager.createQuery(query)
                    .setParameter("startAt", searchParamDTO.getStartAt())
                    .setParameter("endAt", searchParamDTO.getEndAt())
                    .getResultList();
        } else if (searchParamDTO.getStartAt() != null){
            query += "where measurementDate > :startAt";
            rowsWithMeasurements = entityManager.createQuery(query)
                    .setParameter("startAt", searchParamDTO.getStartAt())
                    .getResultList();
        } else if (searchParamDTO.getEndAt() != null){
            query += "where measurementDate < :endAt";
            rowsWithMeasurements = entityManager.createQuery(query)
                    .setParameter("endAt", searchParamDTO.getEndAt())
                    .getResultList();
        } else{
            rowsWithMeasurements = entityManager.createQuery(query)
                    .getResultList();
        }

        if(rowsWithMeasurements.size() == 0){
            return null;
        }

//        List asd = (ArrayList<castModelS>) rowsWithMeasurements;
//        Object asd2 = asd.get(0);
//        testmodel asdss = (testmodel) asd2;

//        rowsWithMeasurements.forEach( mObs -> {
//            Double asd = (Double) mObs[0];
//        });
//        Object[] customData = rowsWithMeasurements.stream().toArray();
//        DataAvgDTO dataAvgDTO = DataAvgDTO.getDataAvg(customData[0]);
        return null;
    }
}