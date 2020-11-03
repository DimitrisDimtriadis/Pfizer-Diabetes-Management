package gr.codehub.teamOne.repository;

import gr.codehub.teamOne.model.Consultation;
import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.repository.lib.Repository;

import javax.persistence.EntityManager;
import java.util.List;

public class ConsultationRepository extends Repository<Consultation, Long> {

    private EntityManager entityManager;

    public ConsultationRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<Consultation> getEntityClass() {
        return Consultation.class;
    }

    @Override
    public String getEntityClassName() {
        return Consultation.class.getName();
    }

    public List getConsultationForUser(long userID){
        return entityManager.createQuery("from Consultation where patient_id = : patientID")
                .setParameter("patientID", userID)
                .getResultList();
    }

    public int calculateUnreadConsultations(Users patient){
        List unReadConsultation = entityManager.createQuery("from Consultation where patient_id = : patientID and isRead = false")
                .setParameter("patientID", patient.getId())
                .getResultList();
        if(unReadConsultation == null){
            return 0;
        } else {
            return unReadConsultation.size();
        }
    }

    public List getPatientThatWaitForNewConsultations(){

        List patientsIdWhichWaitConsultation = entityManager.createQuery("select c.patient.id from Consultation c")
                .getResultList();
//        List patientsIdWhichWaitConsultation = entityManager.createQuery("select max(registerDate) from Consultation c group by patient_id")
//                .getResultList();

        return null;
    }
}
