package gr.codehub.teamOne.representation;

import lombok.Data;

import java.util.Date;

@Data
public class WaitPatConsultationDTO {
    private Long patientsIds;
    private Date lastConsultationDate;
}
