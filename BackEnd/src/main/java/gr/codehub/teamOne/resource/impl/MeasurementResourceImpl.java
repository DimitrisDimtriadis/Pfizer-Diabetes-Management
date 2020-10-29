package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.model.Measurement;
import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.repository.MeasurementsRepository;
import gr.codehub.teamOne.repository.UserRepository;
import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.representation.MeasurementDTO;
import gr.codehub.teamOne.representation.UsersDTO;
import gr.codehub.teamOne.resource.MeasurementResource;
import org.restlet.engine.Engine;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MeasurementResourceImpl extends ServerResource implements MeasurementResource {
    private MeasurementsRepository measurementsRepository;
    private UserRepository userRepository;
    private EntityManager em;

    @Override
    protected void doInit() throws ResourceException {
        try{
            em = JpaUtil.getEntityManager();
            measurementsRepository = new MeasurementsRepository(em);
            userRepository = new UserRepository(em);
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
        List<Measurement> measurementList = measurementsRepository.findAll();

        List<MeasurementDTO> measurementDTOList = new ArrayList<>();
        measurementList.forEach(measurementForUser -> measurementDTOList.add(MeasurementDTO.getMeasurementDTO(measurementForUser)));

        return measurementDTOList;
    }

    @Override
    public void removeMeasurement() throws NotFoundException {

    }

    @Override
    public MeasurementDTO updateMeasurement(MeasurementDTO measurementDTO) throws NotFoundException, BadEntityException {

        if(measurementDTO == null) throw new BadEntityException("Null measurement Exception error");
        Optional<Users>opUser=userRepository.findById(measurementDTO.getUser());
        setExisting(opUser.isPresent());

        if(isExisting()){
            Measurement measurementToUpdate = MeasurementDTO.getMeasurement(measurementDTO);
            measurementToUpdate.setBloodGlucoseLevel(measurementDTO.getBloodGlucoseLevel());
            measurementToUpdate.setCarbIntake(measurementDTO.getCarbIntake());
            measurementToUpdate.setUser(opUser.get());
            measurementsRepository.save(measurementToUpdate);

        }else throw new NotFoundException("Not such user");

        return measurementDTO;
    }

    @Override
    public String addMeasurement(MeasurementDTO measurementDTO) throws NotFoundException, BadEntityException {

        if(measurementDTO == null) throw new BadEntityException("Null measurement Exception error");
        Optional<Users> tempUser = userRepository.findById(measurementDTO.getUser());
        if(!tempUser.isPresent()) throw new NotFoundException("Not such user");

        Measurement measurementToSave = MeasurementDTO.getMeasurement(measurementDTO);
        measurementToSave.setUser(tempUser.get());
        measurementsRepository.save(measurementToSave);
        return "Measurement saved successfully !";
    }
}
