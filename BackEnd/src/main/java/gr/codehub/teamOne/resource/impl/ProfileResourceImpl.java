package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.repository.UserRepository;
import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.representation.UsersDTO;
import gr.codehub.teamOne.resource.ProfileResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class ProfileResourceImpl extends ServerResource implements ProfileResource {

    private EntityManager em;
    private UserRepository userRepository;

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
    public UsersDTO getProfileInfo() throws NotFoundException {

        String usrEmail = this.getRequest().getClientInfo().getUser().getIdentifier();

        Users user = userRepository.getUserInfo(usrEmail);
        if (user != null){
            return UsersDTO.getUsersDTO(user);
        }
        return null;
    }
}
