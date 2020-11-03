package gr.codehub.teamOne.repository;

import gr.codehub.teamOne.Utilities.GeneralFunctions;
import gr.codehub.teamOne.model.Consultation;
import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.repository.lib.Repository;
import gr.codehub.teamOne.representation.WaitPatConsultationDTO;
import gr.codehub.teamOne.representation.WaitPatConsultationResponseDTO;
import org.h2.engine.User;


import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

        if(patientsID.size()>0) {

            StringBuilder customIDs = new StringBuilder("");

            for(int i = 0; i < patientsID.size(); i++){
                if(i!=0){
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

            //Create a new list with all needed info
            patientIdWithOutConsultationEntry.forEach( mId -> {
                wholeLisToCheck.add(WaitPatConsultationDTO.getWaitPatConsultationDTO(mId));
            });
            tempListWithSomePatientIdAndDates.forEach( mRow -> {
                wholeLisToCheck.add(WaitPatConsultationDTO.getWaitPatConsultationDTO((Long) mRow[0], (Date) mRow[1]));
            });

            return getPatientsIdWhichNeedConsultation(wholeLisToCheck);
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

    /**
     * Function that calculate the dates which pass after pass 30 days of consultation period
     *
     * @param listWithAllPatients list with all id and last consultation date (if exist) to decide if it is time to get consultation
     * @return list with patient ids that want consultation from a doctor
     */
    private List<WaitPatConsultationResponseDTO> getPatientsIdWhichNeedConsultation(List<WaitPatConsultationDTO> listWithAllPatients){

        List<WaitPatConsultationResponseDTO> listWithPatientThatWantConsultation = new ArrayList<>();

        listWithAllPatients.forEach( mObj -> {
            if(mObj.getLastConsultationDate() == null){

                Object tempUserList = entityManager.createQuery("select registration_date from Users where id = :userID")
                        .setParameter("userID", mObj.getPatientsId())
                        .getSingleResult();

                if (tempUserList != null){
                    long daysDiff = GeneralFunctions.compareDateWithNow((Date) tempUserList);

                    if (daysDiff >= 30){

                        WaitPatConsultationResponseDTO flName = getFirstnameAndLastname(mObj.getPatientsId());

                        WaitPatConsultationResponseDTO tempObj = new WaitPatConsultationResponseDTO();
                        tempObj.setPatientsId(mObj.getPatientsId());
                        tempObj.setDaysFromLastConsultation(daysDiff);
                        tempObj.setFirst_name(flName.getFirst_name());
                        tempObj.setLast_name(flName.getLast_name());
                        listWithPatientThatWantConsultation.add(tempObj);
                    }
                }
            } else {
                long daysDiff = GeneralFunctions.compareDateWithNow(mObj.getLastConsultationDate());

                WaitPatConsultationResponseDTO flName = getFirstnameAndLastname(mObj.getPatientsId());

                if (daysDiff >= 30){
                    WaitPatConsultationResponseDTO tempObj = new WaitPatConsultationResponseDTO();
                    tempObj.setPatientsId(mObj.getPatientsId());
                    tempObj.setDaysFromLastConsultation(daysDiff);
                    tempObj.setFirst_name(flName.getFirst_name());
                    tempObj.setLast_name(flName.getLast_name());
                    listWithPatientThatWantConsultation.add(tempObj);
                }
            }
        });

        return listWithPatientThatWantConsultation;
    }

    private WaitPatConsultationResponseDTO getFirstnameAndLastname(Long mUserId){

        WaitPatConsultationResponseDTO tempObj = new WaitPatConsultationResponseDTO();

        Object tUser = entityManager.createQuery("select first_name, last_name from Users where id = :userID")
                .setParameter("userID", mUserId)
                .getSingleResult();

        Object[] mUser = (Object[]) tUser;
        tempObj.setFirst_name((String) mUser[0]);
        tempObj.setLast_name((String) mUser[1]);
        return tempObj;
    }
}
