package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.repository.PatientRepository;
import gr.codehub.teamOne.repository.UserRepository;
import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.representation.PatientDTO;
import gr.codehub.teamOne.representation.UsersDTO;
import gr.codehub.teamOne.resource.PatientResurce;
import gr.codehub.teamOne.security.AccessRole;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class PatientResourceImpl extends ServerResource implements PatientResurce {
    private PatientRepository patientRepository;
    private EntityManager em;


    @Override
    protected void doInit() throws ResourceException {

        try {
            em = JpaUtil.getEntityManager();
            patientRepository = new PatientRepository(em);

        } catch (Exception e) {
            throw new ResourceException(e);
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public List<PatientDTO> getsPatients() throws NotFoundException {
        List<Users> patientList = patientRepository.findAll();

        List<PatientDTO> patientDTOList = new ArrayList<>();
        patientList.forEach(patient -> patientDTOList.add(PatientDTO.getPatientDTO(patient)));

        return patientDTOList;    }
}
