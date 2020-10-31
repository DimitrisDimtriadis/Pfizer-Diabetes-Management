package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.WrongUserRoleException;
import gr.codehub.teamOne.model.PatientDoctorAssociation;
import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.repository.PatientDoctorAssociationRepository;
import gr.codehub.teamOne.repository.UserRepository;
import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.representation.PatientDoctorAssociationDTO;
import gr.codehub.teamOne.resource.PatientDoctorAssociationResource;
import gr.codehub.teamOne.security.AccessRole;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientDoctorAssociationResourceImpl extends ServerResource implements PatientDoctorAssociationResource {

    private UserRepository userRepository;
    private PatientDoctorAssociationRepository associationRepository;
    private EntityManager em;
    private Long categoryType;

    @Override
    protected void doInit() throws ResourceException {
        try{
            em = JpaUtil.getEntityManager();
            userRepository = new UserRepository(em);
            associationRepository = new PatientDoctorAssociationRepository(em);

            String tempCategory = getQueryValue("categoryType");
            if (tempCategory != null){
                categoryType = Long.parseLong(getQueryValue("categoryType"));
            } else {
                categoryType = null;
            }
        }catch(Exception e){
            throw new ResourceException(e);
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    /**
     * Base on categoryType (url attribute) user gives, it return a list
     * null = All associations
     * 1 = patients without Doctor
     * 2 = patients with Doctors
     *
     * @return List with association
     */
    @Override
    public List<PatientDoctorAssociationDTO> getAllAssociations() throws BadEntityException {

        List<PatientDoctorAssociationDTO> tempListAssociationsDTO = new ArrayList<>();
        List<PatientDoctorAssociation> associationsList;

        if(categoryType == null){
            associationsList = associationRepository.findAll();
        } else if(categoryType == 2){
            associationsList = associationRepository.getPatientWithoutDoctor(true);
        } else if(categoryType == 1){
            associationsList = associationRepository.getPatientWithoutDoctor(false);
        } else {
            throw new BadEntityException("Wrong categoryType url attribute");
        }

        associationsList.forEach(mObj -> tempListAssociationsDTO.add(PatientDoctorAssociationDTO.getAssociation(mObj)));

        return tempListAssociationsDTO;
    }

    @Override
    public PatientDoctorAssociationDTO addNewAssociation(PatientDoctorAssociationDTO newAssociationDTO) throws BadEntityException, WrongUserRoleException {

        if (newAssociationDTO == null) throw new BadEntityException("Null userException error");

        //Take saved association(if exist)
        PatientDoctorAssociation mAssociation = associationRepository.getAssociationIfExist(newAssociationDTO.getPatient());

        //If there is no entry, Create a new one
        if(mAssociation == null){
            mAssociation = PatientDoctorAssociationDTO.getAssociation(newAssociationDTO);
        }

        Optional<Users> patient = userRepository.findById(newAssociationDTO.getPatient());
        if(!patient.isPresent()) throw new BadEntityException("There is no patient with that id");

        if(patient.get().getAccountType() != AccessRole.ROLE_PATIENT) throw new WrongUserRoleException("The user you add as patient, has wrong role");
        mAssociation.setPatient(patient.get());

        if(newAssociationDTO.getDoctor() != null ){

            Optional<Users> doctor = userRepository.findById(newAssociationDTO.getDoctor());
            if(!doctor.isPresent()) throw new BadEntityException("There is no doctor with that id");
            if(doctor.get().getAccountType() != AccessRole.ROLE_DOCTOR) throw new WrongUserRoleException("The user you add as doctor, has wrong role");
            mAssociation.setDoctor(doctor.get());
        }

        associationRepository.save(mAssociation);
        return PatientDoctorAssociationDTO.getAssociation(mAssociation);
    }
}