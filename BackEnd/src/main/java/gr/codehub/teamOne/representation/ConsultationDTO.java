package gr.codehub.teamOne.representation;

import gr.codehub.teamOne.model.Consultation;
import lombok.Data;

import java.util.Date;

@Data
public class ConsultationDTO {

    private Long consultationID;
    private long patientID;
    private Date registerDate;
    private String consultationMsg;
    private Boolean isRead;

    public static Consultation getConsultation(ConsultationDTO consultationDTO){

        Consultation consultation = new Consultation();
        consultation.setRegisterDate(new Date());
        consultation.setConsultationMsg(consultationDTO.getConsultationMsg());
        consultation.setRead(false);

        return consultation;
    }

    public static ConsultationDTO getConsultationDTO(Consultation consultation){

        ConsultationDTO consultationDTO = new ConsultationDTO();
        consultationDTO.setPatientID(consultation.getPatient().getId());
        consultationDTO.setRegisterDate(consultation.getRegisterDate());
        consultationDTO.setConsultationMsg(consultationDTO.getConsultationMsg());
        consultationDTO.setIsRead(consultation.isRead());

        return consultationDTO;
    }
}
