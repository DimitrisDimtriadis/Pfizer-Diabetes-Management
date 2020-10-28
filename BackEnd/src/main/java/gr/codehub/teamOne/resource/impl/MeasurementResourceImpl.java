package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.model.Measurement;
import gr.codehub.teamOne.repository.MeasurementsRepository;
import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.representation.MeasurementDTO;
import gr.codehub.teamOne.resource.MeasurementResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.List;

public class MeasurementResourceImpl extends ServerResource implements MeasurementResource {

    private MeasurementsRepository measurementsRepository;
    private EntityManager em;

    @Override
    protected void doInit() throws ResourceException {
        try{
            em = JpaUtil.getEntityManager();
            measurementsRepository = new MeasurementsRepository(em);
        }catch(Exception e) {
            throw new ResourceException(e);
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public List<MeasurementDTO> getMeasurementForUser() throws NotFoundException {
        return null;
    }

    @Override
    public void removeMeasurement() throws NotFoundException {

    }

    @Override
    public MeasurementDTO updateMeasurement(MeasurementDTO measurementDTO) throws NotFoundException, BadEntityException {
        return null;
    }

    @Override
    public MeasurementDTO addMeasurement(MeasurementDTO measurementDTO) throws NotFoundException, BadEntityException {

        if(measurementDTO == null) throw new BadEntityException("Null measurement Exception error");

        Measurement measurement = MeasurementDTO.getMeasurement(measurementDTO);
        measurementsRepository.save(measurement);
        return null;
    }
}
