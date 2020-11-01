package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.Utilities.GeneralFunctions;
import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.exceptions.WrongUserRoleException;
import gr.codehub.teamOne.model.PatientDoctorAssociation;
import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.repository.PatientDoctorAssociationRepository;
import gr.codehub.teamOne.repository.UserRepository;
import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.representation.LoginCredentialDTO;
import gr.codehub.teamOne.representation.PatientDoctorAssociationDTO;
import gr.codehub.teamOne.representation.UsersDTO;
import gr.codehub.teamOne.resource.interfaces.LoginRegisterResource;
import gr.codehub.teamOne.security.AccessRole;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoginRegisterResourceImpl extends ServerResource implements LoginRegisterResource {

    private UserRepository userRepository;
    private PatientDoctorAssociationRepository associationRepository;
    private EntityManager em;

    @Override
    protected void doInit() throws ResourceException {

        try {
            em = JpaUtil.getEntityManager();
            userRepository = new UserRepository(em);
            associationRepository = new PatientDoctorAssociationRepository(em);
        } catch (Exception e) {
            throw new ResourceException(e);
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }
    /**
     * Method to get all the users from base
     *
     * @return Users Representation List of objects
     */
    @Override
    public List<UsersDTO> getsUsers() throws NotFoundException {

        List<Users> usersList = GeneralFunctions.removeInactiveUsers(userRepository.findAll());

        List<UsersDTO> usersDTOList = new ArrayList<>();

        usersList.forEach(users -> usersDTOList.add(UsersDTO.getUsersDTO(users)));

        return usersDTOList;
    }

    /**
     * Method to validate user existence in base
     *
     * @param loginCredentialDTO Object with login credentials for login
     * @return AccessRole to notify frontEnd about type of account
     * @throws NotFoundException When there is no user with this credentials
     * @throws BadEntityException When input is null
     */
    @Override
    public AccessRole loginUser(LoginCredentialDTO loginCredentialDTO) throws NotFoundException, BadEntityException {

        if (loginCredentialDTO == null) throw new BadEntityException("Null userException error");

        List<Users> listWithUsers = userRepository.findUserWithCredential(loginCredentialDTO);
        listWithUsers = GeneralFunctions.removeInactiveUsers(listWithUsers);
        if (listWithUsers.size() == 0) throw new NotFoundException("User account not found !");

        //Update the lastLogin to keep last entry of user
        Users userToLogin = listWithUsers.get(0);
        userToLogin.setLastLogin(new Date());
        userRepository.save(userToLogin);
        return userToLogin.getAccountType();
    }

    /**
     * Method to add account to base
     *
     * @param usersDTO Representation object to save it on base
     * @return Representation object that save in base
     * @throws BadEntityException For wrong object as input
     */
    @Override
    public UsersDTO registerUser(UsersDTO usersDTO) throws BadEntityException {

        if (usersDTO == null) throw new BadEntityException("Null userException error");
        if (userRepository.checkIfAccountExist(usersDTO))
            throw new BadEntityException("Found entry with the same AMKA or email");

        Users users = UsersDTO.getUsers(usersDTO);
        users.setActive(true);
        userRepository.save(users);

        //TODO: Check if users is inActive! make it active and do update
        //To add entry on association
        if(users.getAccountType() == AccessRole.ROLE_PATIENT){
            createNewEntryOnAssociationForPatient(users);
        }
        return UsersDTO.getUsersDTO(users);
    }

    private void createNewEntryOnAssociationForPatient(Users newPatient) throws BadEntityException {

        PatientDoctorAssociation mAssociation = new PatientDoctorAssociation();

        mAssociation.setPatient(newPatient);
        mAssociation.setActive(true);

        associationRepository.save(mAssociation);
    }
}
