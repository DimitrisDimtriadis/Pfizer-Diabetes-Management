package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.model.Consultation;
import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.repository.ConsultationRepository;
import gr.codehub.teamOne.repository.UserRepository;
import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.representation.ConsultationDTO;
import gr.codehub.teamOne.representation.ConsultationDeleteDTO;
import gr.codehub.teamOne.resource.ConsultationResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsultationResourceImpl extends ServerResource implements ConsultationResource {

    private EntityManager em;
    private ConsultationRepository consultationRepository;
    private UserRepository userRepository;
    private Long categoryType;

    @Override
    protected void doInit() throws ResourceException {

        try {

            em = JpaUtil.getEntityManager();
            consultationRepository = new ConsultationRepository(em);
            userRepository = new UserRepository(em);
            String tempCategory = getQueryValue("categoryType");
            categoryType = (tempCategory != null) ? Long.parseLong(getQueryValue("categoryType")) : null;

        } catch (Exception e) {
            throw new ResourceException(e);
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    /**
     * Get consultation for specific users based on url attribute categoryType
     * null = users that call url consultations
     * -1 = All consultations
     * x(id) = All consultations for x user
     *
     * @return List with consultations
     * @throws BadEntityException User give wrong patientID
     */
    @Override
    public List<ConsultationDTO> getConsultation() throws BadEntityException {

        List<ConsultationDTO> tempListConsultationDTO = new ArrayList<>();
        List<Consultation> consultationList;

        if (categoryType == null) {
            String usrEmail = this.getRequest().getClientInfo().getUser().getIdentifier();
            Optional<Users> tempUsr = userRepository.findByEmail(usrEmail);
            if (!tempUsr.isPresent()) throw new BadEntityException("There is no user with this email");
            consultationList = consultationRepository.getConsultationForUser(tempUsr.get().getId());
        } else if (categoryType == -1) {
            consultationList = consultationRepository.findAll();
        } else {
            consultationList = consultationRepository.getConsultationForUser(categoryType);
        }
        consultationList.forEach(mObj -> tempListConsultationDTO.add(ConsultationDTO.getConsultationDTO(mObj)));
        return tempListConsultationDTO;
    }

    @Override
    public String addConsultation(ConsultationDTO consultationDTO) throws BadEntityException {

        Consultation consultation = ConsultationDTO.getConsultation(consultationDTO);
        Optional<Users> users = userRepository.findById(consultationDTO.getPatientID());

        if (!users.isPresent()) throw new BadEntityException("No patient with this id");

        consultation.setPatient(users.get());
        consultationRepository.save(consultation);

        return "New consultation added";
    }

    @Override
    public String updateConsultation(ConsultationDTO consultationDTO) throws BadEntityException {

        Optional<Consultation> consultation = consultationRepository.findById(consultationDTO.getConsultationID());
        if (!consultation.isPresent()) throw new BadEntityException("The is no consultation with this id");

        consultation.get().setId(consultationDTO.getConsultationID());

        Optional<Users> users = userRepository.findById(consultationDTO.getPatientID());
        if (!users.isPresent()) throw new BadEntityException("No patient with this id");

        consultation.get().setPatient(users.get());
        Consultation model = ConsultationDTO.updateModel(consultation.get(), consultationDTO);
        consultationRepository.save(model);

        return "Consultation updated";
    }

    @Override
    public String deleteConsultation(ConsultationDeleteDTO consultationDeleteDTO) throws BadEntityException {

        if (consultationDeleteDTO == null) throw new BadEntityException("Null object as input");
        consultationRepository.deleteById(consultationDeleteDTO.getConsultationID());
        return "Consultation deleted";
    }
}
