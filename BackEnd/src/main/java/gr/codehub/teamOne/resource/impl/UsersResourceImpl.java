package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.repository.UserRepository;
import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.representation.UsersDTO;
import gr.codehub.teamOne.resource.UsersResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class UsersResourceImpl extends ServerResource implements UsersResource {

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
    public UsersDTO findUserByAmka(UsersDTO usersDTO) throws NotFoundException {

        Users person = userRepository.getUserBasedOnAmka(usersDTO);

        //TODO: Check if there is no entry
        if (person != null) throw new NotFoundException("There is no user with this amka");
        return UsersDTO.getUsersDTO(person);
    }
}
