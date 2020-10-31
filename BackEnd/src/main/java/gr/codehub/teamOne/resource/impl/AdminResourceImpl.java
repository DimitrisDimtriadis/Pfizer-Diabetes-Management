package gr.codehub.teamOne.resource.impl;


import gr.codehub.teamOne.Utilities.GeneralFunctions;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.repository.UserRepository;
import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.representation.DoctorsDTO;
import gr.codehub.teamOne.resource.AdminResource;
import gr.codehub.teamOne.resource.util.ResourceUtils;
import gr.codehub.teamOne.security.AccessRole;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.List;

public class AdminResourceImpl  extends ServerResource implements AdminResource {
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
    public List<DoctorsDTO> checkDoctorOfflinePeriod() throws NotFoundException {
        ResourceUtils.checkRole(this, GeneralFunctions.rolesWithAccess(false, false, true));
        List<Users> doctorList = userRepository.getAllUsersBasedOnRole(AccessRole.ROLE_DOCTOR);

        if(doctorList.get().)


        return null ;
    }
}
