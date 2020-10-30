package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.Utilities.GeneralFunctions;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.repository.UserRepository;
import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.representation.PatientDTO;
import gr.codehub.teamOne.resource.PatientResource;
import gr.codehub.teamOne.resource.util.ResourceUtils;
import gr.codehub.teamOne.security.AccessRole;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class PatientResourceImpl extends ServerResource implements PatientResource {

    private UserRepository userRepository;
    private EntityManager em;

    @Override
    protected void doInit() throws ResourceException {

        try {
            em = JpaUtil.getEntityManager();
            userRepository = new UserRepository(em);

        } catch (Exception e) {
            throw new ResourceException(e);
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    /**
     * Method to get all patients, checking the role .
     * @return Patient Representation Object List .
     */
    @Override
    public List<PatientDTO> getsPatients() throws NotFoundException {

        ResourceUtils.checkRole(this, GeneralFunctions.rolesWithAccess(true, true, true));
        List<Users> patientList = userRepository.getAllUsersBasedOnRole(AccessRole.ROLE_PATIENT);

        List<PatientDTO> patientDTOList = new ArrayList<>();
        patientList.forEach(patient -> patientDTOList.add(PatientDTO.getPatientDTO(patient)));

        return patientDTOList;    }
}
