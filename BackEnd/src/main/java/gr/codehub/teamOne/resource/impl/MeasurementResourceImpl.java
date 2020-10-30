package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.model.Measurement;
import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.repository.MeasurementsRepository;
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
    /**
     * Method to get measurements for a specific user using email to identify the user.
     * @param paramDTO Object of MeasurementsSearchParamDTO.
     * @return Measurement Representation Object List with measurements for the user.
     * @throws NotFoundException,BadEntityException
     */
    @Override
    public List<MeasurementDTO> getMeasurementForUser(MeasurementsSearchParamDTO paramDTO) throws NotFoundException, BadEntityException {

        String usrEmail = this.getRequest().getClientInfo().getUser().getIdentifier();
        Users currentUser = userRepository.getUserInfo(usrEmail);

        paramDTO.setUserID(currentUser.getId());

        List<Measurement> listWithMeasurements = measurementsRepository.getSpecificMeasurements(paramDTO);
        List<MeasurementDTO> listWithDTO = new ArrayList<>();
        listWithMeasurements.forEach( ms -> listWithDTO.add(MeasurementDTO.getMeasurementDTO(ms)));
        return listWithDTO;
    }
    /**
     * Method that remove a measurement.
     * @param measurementDTO Object of DeleteMeasurementDTO.
     * @return A message that measurement has successfully deleted.
     * @throws NotFoundException,BadEntityException
     */
    @Override
    public String removeMeasurement(DeleteMeasurementDTO measurementDTO) throws NotFoundException, BadEntityException {

        if (measurementDTO==null) throw new BadEntityException("Null object as input");
        measurementsRepository.deleteById(measurementDTO.getMeasurementID());
        return "Successfully deleted";
    }

    /**
     * Method that update a measurement.
     * @param measurementDTO Object of Measurement Representation.
     * @return A Measurement Representation Object with the new values .
     * @throws NotFoundException,BadEntityException
     */
    @Override
    public MeasurementDTO updateMeasurement(MeasurementDTO measurementDTO) throws NotFoundException, BadEntityException {

        if(measurementDTO == null) throw new BadEntityException("Null measurement Exception error");
        if(measurementDTO.getMeasurementID() == null) throw new BadEntityException("No measurement id to update");

        Optional<Measurement> demandMeasurement = measurementsRepository.findById(measurementDTO.getMeasurementID());
        if(!demandMeasurement.isPresent()) throw new NotFoundException("Not such measure");

        Measurement measurementToUpdate = MeasurementDTO.updateMeasurement(demandMeasurement.get(), measurementDTO);
        measurementsRepository.save(measurementToUpdate);

        return measurementDTO;
    }
    /**
     * Method that add a measurement and using email to identify the user .
     * @param measurementDTO Object of Measurement Representation.
     * @return A message that measurement saved successfully .
     * @throws NotFoundException,BadEntityException
     */
    @Override
    public String addMeasurement(MeasurementDTO measurementDTO) throws NotFoundException, BadEntityException {

        String usrEmail = this.getRequest().getClientInfo().getUser().getIdentifier();

        if(measurementDTO == null) throw new BadEntityException("Null measurement Exception error");

        Optional<Users> demandedUser = userRepository.findByEmail(usrEmail);

        if(!demandedUser.isPresent()) throw new NotFoundException("Not such user");

        measurementDTO.setUser(demandedUser.get().getId());

        Measurement measurementToSave = MeasurementDTO.getMeasurement(measurementDTO);
        measurementToSave.setUser(demandedUser.get());
        measurementsRepository.save(measurementToSave);
        return "Measurement saved successfully !";
    }

    @Override
    public List<MeasurementDTO> getAllMeasurementsBasedOn(MeasurementsSearchParamDTO paramDTO) throws NotFoundException, BadEntityException {

        List<Measurement> listWithMeasurements = measurementsRepository.getSpecificMeasurements(paramDTO);
        List<MeasurementDTO> listWithDTO = new ArrayList<>();
        listWithMeasurements.forEach( ms -> listWithDTO.add(MeasurementDTO.getMeasurementDTO(ms)));

        return listWithDTO;
    }
}
