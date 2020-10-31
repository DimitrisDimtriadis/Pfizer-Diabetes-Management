package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.model.Measurement;
import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.repository.MeasurementRepository;
import gr.codehub.teamOne.repository.UserRepository;
import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.representation.DeleteMeasurementDTO;
import gr.codehub.teamOne.representation.MeasurementDTO;
import gr.codehub.teamOne.representation.MeasurementsSearchParamDTO;
import gr.codehub.teamOne.resource.MeasurementResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MeasurementResourceImpl extends ServerResource implements MeasurementResource {
    private MeasurementRepository measurementRepository;
    private UserRepository userRepository;
    private EntityManager em;

    @Override
    protected void doInit() throws ResourceException {
        try{
            em = JpaUtil.getEntityManager();
            measurementRepository = new MeasurementRepository(em);
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
    public List<MeasurementDTO> getMeasurementForUser(MeasurementsSearchParamDTO paramDTO) throws NotFoundException, BadEntityException {

        String usrEmail = this.getRequest().getClientInfo().getUser().getIdentifier();
        Users currentUser = userRepository.getUserInfo(usrEmail);

        paramDTO.setUserID(currentUser.getId());

        List<Measurement> listWithMeasurements = measurementRepository.getSpecificMeasurements(paramDTO);
        List<MeasurementDTO> listWithDTO = new ArrayList<>();
        listWithMeasurements.forEach( ms -> listWithDTO.add(MeasurementDTO.getMeasurementDTO(ms)));
        return listWithDTO;
    }

    @Override
    public String removeMeasurement(DeleteMeasurementDTO measurementDTO) throws NotFoundException, BadEntityException {

        if (measurementDTO==null) throw new BadEntityException("Null object as input");
        measurementRepository.deleteById(measurementDTO.getMeasurementID());
        return "Successfully deleted";
    }

    @Override
    public MeasurementDTO updateMeasurement(MeasurementDTO measurementDTO) throws NotFoundException, BadEntityException {

        if(measurementDTO == null) throw new BadEntityException("Null measurement Exception error");
        if(measurementDTO.getMeasurementID() == null) throw new BadEntityException("No measurement id to update");

        Optional<Measurement> demandMeasurement = measurementRepository.findById(measurementDTO.getMeasurementID());
        if(!demandMeasurement.isPresent()) throw new NotFoundException("Not such measure");

        Measurement measurementToUpdate = MeasurementDTO.updateMeasurement(demandMeasurement.get(), measurementDTO);
        measurementRepository.save(measurementToUpdate);

        return measurementDTO;
    }

    @Override
    public String addMeasurement(MeasurementDTO measurementDTO) throws NotFoundException, BadEntityException {

        String usrEmail = this.getRequest().getClientInfo().getUser().getIdentifier();

        if(measurementDTO == null) throw new BadEntityException("Null measurement Exception error");

        Optional<Users> demandedUser = userRepository.findByEmail(usrEmail);

        if(!demandedUser.isPresent()) throw new NotFoundException("Not such user");

        measurementDTO.setUser(demandedUser.get().getId());

        Measurement measurementToSave = MeasurementDTO.getMeasurement(measurementDTO);
        measurementToSave.setUser(demandedUser.get());
        measurementRepository.save(measurementToSave);
        return "Measurement saved successfully !";
    }

    @Override
    public List<MeasurementDTO> getAllMeasurementsBasedOn(MeasurementsSearchParamDTO paramDTO) throws NotFoundException, BadEntityException {

        List<Measurement> listWithMeasurements = measurementRepository.getSpecificMeasurements(paramDTO);
        List<MeasurementDTO> listWithDTO = new ArrayList<>();
        listWithMeasurements.forEach( ms -> listWithDTO.add(MeasurementDTO.getMeasurementDTO(ms)));

        return listWithDTO;
    }
}
