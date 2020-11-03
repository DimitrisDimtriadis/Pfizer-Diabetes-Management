package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.repository.ConsultationRepository;
import gr.codehub.teamOne.repository.PatientDoctorAssociationRepository;
import gr.codehub.teamOne.repository.UserRepository;
import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.representation.UsersDTO;
import gr.codehub.teamOne.representation.WaitPatConsultationDTO;
import gr.codehub.teamOne.resource.interfaces.WaitConsultationResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.List;

public class WaitConsultationResourceImpl extends ServerResource implements WaitConsultationResource {

    private EntityManager em;
    private UserRepository userRepository;
    private ConsultationRepository consultationRepository;
    private PatientDoctorAssociationRepository associationRepository;

    @Override
    protected void doInit() throws ResourceException {
        try{
            em = JpaUtil.getEntityManager();
            userRepository = new UserRepository(em);
            consultationRepository = new ConsultationRepository(em);
            associationRepository = new PatientDoctorAssociationRepository(em);
        }catch (Exception e){
            throw new ResourceException(e);
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public List<UsersDTO> getPatientsWaiting() {

        List<Long> patientsId = associationRepository.getIdsOfPatients();
        consultationRepository.getPatientThatWaitForNewConsultations(patientsId);
        return null;
    }
}
