package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.repository.UserRepository;
import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.representation.LoginCredentialDTO;
import gr.codehub.teamOne.representation.UsersDTO;
import gr.codehub.teamOne.resource.LoginRegisterResource;
import gr.codehub.teamOne.security.AccessRole;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class LoginRegisterResourceImpl extends ServerResource implements LoginRegisterResource {

    private UserRepository userRepository;
    private EntityManager em;

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
    public List<UsersDTO> getsUsers() throws NotFoundException {

        List<Users> usersList = userRepository.findAll();

        List<UsersDTO> usersDTOList = new ArrayList<>();
        usersList.forEach( users -> usersDTOList.add(UsersDTO.getUsersDTO(users)));

        return usersDTOList;
    }

    @Override
    public AccessRole verifyUser(LoginCredentialDTO loginCredentialDTO) throws NotFoundException, BadEntityException {

//        if(loginCredentialDTO == null) throw new BadEntityException("Null userException error");
//        if(userRepository.checkIfAccountExist(loginCredentialDTO)) throw new BadEntityException("Found entry with the same AMKA or email");
//
//        Users users = UsersDTO.getUsers(usersDTO);
//        userRepository.save(users);
//        return UsersDTO.getUsersDTO(users);
        return null;
    }

    @Override
    public UsersDTO addUser(UsersDTO usersDTO) throws NotFoundException, BadEntityException {

        //ResourceUtils.checkRole(this, GeneralFunctions.rolesWithAccess(false, true, true));
        if(usersDTO == null) throw new BadEntityException("Null userException error");
        if(userRepository.checkIfAccountExist(usersDTO)) throw new BadEntityException("Found entry with the same AMKA or email");

        Users users = UsersDTO.getUsers(usersDTO);
        userRepository.save(users);
        return UsersDTO.getUsersDTO(users);
    }

    @Override
    public void removeUser() throws NotFoundException {

    }
}
