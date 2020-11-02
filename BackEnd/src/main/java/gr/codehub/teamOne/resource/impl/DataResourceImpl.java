package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.repository.MeasurementRepository;
import gr.codehub.teamOne.repository.UserRepository;
import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.representation.MeasurementsSearchParamDTO;
import gr.codehub.teamOne.resource.interfaces.DataResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class DataResourceImpl extends ServerResource implements DataResource {

    private EntityManager em;
    private MeasurementRepository measurementRepository;
    private UserRepository userRepository;

    @Override
    protected void doInit() throws ResourceException {
        try{
            em = JpaUtil.getEntityManager();
            measurementRepository = new MeasurementRepository(em);
            userRepository = new UserRepository(em);
        } catch (Exception e){
            throw new ResourceException(e);
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public String getAvgData(MeasurementsSearchParamDTO measurementsSearchParamDTO) {
        measurementRepository.calculateAvgOfData(measurementsSearchParamDTO);
        return null;
    }
}
