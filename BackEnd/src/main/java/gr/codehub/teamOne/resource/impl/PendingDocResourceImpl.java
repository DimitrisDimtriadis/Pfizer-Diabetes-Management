package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.repository.UserRepository;
import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.representation.UsersDTO;
import gr.codehub.teamOne.resource.interfaces.PendingDocResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class PendingDocResourceImpl extends ServerResource implements PendingDocResource {

    private EntityManager em;
    private UserRepository userRepository;

    @Override
    protected void doInit() throws ResourceException {
        try{
            em = JpaUtil.getEntityManager();
            userRepository = new UserRepository(em);
        }catch(Exception e){
            throw new ResourceException(e);
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public List<UsersDTO> getAllPendingDoctors() {
        List<Users> pendingDocs = userRepository.getAllPendingDoctors();
        List<UsersDTO> tempList = new ArrayList<>();
        pendingDocs.forEach(doc -> {
            tempList.add(UsersDTO.getUsersDTO(doc));
        });
        return tempList;
    }
}
