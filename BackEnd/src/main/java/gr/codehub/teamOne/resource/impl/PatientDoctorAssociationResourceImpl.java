package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.model.PatientDoctorAssociation;
import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.repository.PatientDoctorAssociationRepository;
import gr.codehub.teamOne.repository.UserRepository;
import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.representation.PatientDoctorAssociationDTO;
import gr.codehub.teamOne.resource.PatientDoctorAssociationResource;
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

    @Override
    protected void doInit() throws ResourceException {
        try{
            em = JpaUtil.getEntityManager();
            userRepository = new UserRepository(em);
            associationRepository = new PatientDoctorAssociationRepository(em);
        }catch(Exception e){
            throw new ResourceException(e);
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public List<PatientDoctorAssociationDTO> getAllAssociations() {

        List<PatientDoctorAssociation> asd = associationRepository.findAll();
        List<PatientDoctorAssociationDTO> templist = new ArrayList<>();
        asd.forEach(mObj -> templist.add(PatientDoctorAssociationDTO.getAssociation(mObj)));

        return templist;
    }

    @Override
    public PatientDoctorAssociationDTO addNewAssociation(PatientDoctorAssociationDTO newAssociationDTO) throws BadEntityException {

        //TODO: add check for roles
        if (newAssociationDTO == null) throw new BadEntityException("Null userException error");

        PatientDoctorAssociation mAssociation = PatientDoctorAssociationDTO.getAssociation(newAssociationDTO);

        Optional<Users> patient = userRepository.findById(newAssociationDTO.getPatientID());
        if(!patient.isPresent()) throw new BadEntityException("There is no patient with that id");
        mAssociation.setPatientID(patient.get());

        if(newAssociationDTO.getDoctorID() != null ){

            Optional<Users> doctor = userRepository.findById(newAssociationDTO.getDoctorID());
            if(!doctor.isPresent()) throw new BadEntityException("There is no doctor with that id");
            mAssociation.setDoctorID(doctor.get());
        }

        associationRepository.save(mAssociation);
        return PatientDoctorAssociationDTO.getAssociation(mAssociation);
    }
}