package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.model.Consultation;
import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.repository.ConsultationRepository;
import gr.codehub.teamOne.repository.UserRepository;
import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.representation.ConsultationDTO;
import gr.codehub.teamOne.resource.ConsultationResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ConsultationResourceImpl extends ServerResource implements ConsultationResource {

    private EntityManager em;
    private ConsultationRepository consultationRepository;
    private UserRepository userRepository;

    @Override
    protected void doInit() throws ResourceException {

        try{
            em = JpaUtil.getEntityManager();
            consultationRepository = new ConsultationRepository(em);
            userRepository = new UserRepository(em);
        }catch (Exception e){
            throw new ResourceException(e);
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public List<Consultation> getConsultation() {

        return null;
    }

    @Override
    public ConsultationDTO addConsultation(ConsultationDTO consultationDTO) throws BadEntityException {

        Consultation consultation = ConsultationDTO.getConsultation(consultationDTO);
        Optional<Users> users = userRepository.findById(consultationDTO.getPatientID());

        if(!users.isPresent()) throw new BadEntityException("No patient with this id");

        consultation.setPatient(users.get());
        consultationRepository.save(consultation);

        return ConsultationDTO.getConsultationDTO(consultation);
    }
}
