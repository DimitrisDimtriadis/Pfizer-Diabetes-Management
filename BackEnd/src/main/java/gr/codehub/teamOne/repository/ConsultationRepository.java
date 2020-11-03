package gr.codehub.teamOne.repository;

import gr.codehub.teamOne.model.Consultation;
import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.repository.lib.Repository;
import gr.codehub.teamOne.representation.WaitPatConsultationDTO;

import javax.persistence.EntityManager;
import java.util.ArrayList;
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

    public List calculateUnreadConsultations(Users patient){
        return entityManager.createQuery("from Consultation where patient_id = : patientID and isRead = false")
                .setParameter("patientID", patient.getId())
                .getResultList();
    }

    public List getPatientThatWaitForNewConsultations(List<Long> patientsID){

        //TODO: Work here !!!
        if(patientsID.size()>0) {

            StringBuilder customIDs = new StringBuilder("");

            for(int i = 0; i < patientsID.size(); i++){
                if(i!=0 || i != patientsID.size()-1){
                    customIDs.append(", ");
                }
                customIDs.append(patientsID.get(i));
            }

            String mQuery = "select patient.id, max(registerDate) from Consultation c where patient.id in ("+ customIDs +") group by patient.id";
            List patientsIdWhichWaitConsultation = entityManager.createQuery(mQuery)
                    .getResultList();

            //Rows from Consultation with last entry date
            List<Object[]> tempListWithSomePatientIdAndDates = patientsIdWhichWaitConsultation;

            List<Long> listFromConsultationsWithPatientID = new ArrayList<>();

            tempListWithSomePatientIdAndDates.forEach(mRow -> {
                listFromConsultationsWithPatientID.add((Long) mRow[0]);
            });

            // Ids that doesn't have any entry in consultation
            List<Long> patientIdWithOutConsultationEntry = new ArrayList<>();
            patientIdWithOutConsultationEntry = getIdThatAreNotInConsultationTable(patientsID, listFromConsultationsWithPatientID);

            List<WaitPatConsultationDTO> wholeLisToCheck = new ArrayList<>();


            return null;
        }
        return null;
    }

    /**
     * Compere 2 list<Long> to find which id missing
     *
     * @param allPatientsId All ids from patients in Association table
     * @param patientsIdFromConsultations All ids from patients in Consultation
     * @return
     */
    private List<Long> getIdThatAreNotInConsultationTable(List<Long> allPatientsId, List<Long> patientsIdFromConsultations){

        patientsIdFromConsultations.forEach( mId -> {
            allPatientsId.remove(mId);
        });

        return allPatientsId;
    }


}
