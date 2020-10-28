package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.repository.UserRepository;
import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.representation.DoctorDTO;
import gr.codehub.teamOne.representation.PatientDTO;
import gr.codehub.teamOne.resource.DoctorResource;
import gr.codehub.teamOne.security.AccessRole;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class DoctorResourceImpl extends ServerResource implements DoctorResource {



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

    @Override
    public List<DoctorDTO> getsDoctors() throws NotFoundException {

        List<Users> doctorList = userRepository.getAllUsersBasedOnRole(AccessRole.ROLE_DOCTOR);

        List<DoctorDTO> doctorDTOList = new ArrayList<>();
        doctorList.forEach(doctors -> doctorDTOList.add(DoctorDTO.getDoctorDTO(doctors)));

        return doctorDTOList;
}







}
