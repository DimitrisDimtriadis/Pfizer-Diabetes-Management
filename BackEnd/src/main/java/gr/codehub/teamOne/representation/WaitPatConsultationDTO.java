package gr.codehub.teamOne.representation;

import lombok.Data;

import java.util.List;

@Data
public class WaitPatConsultationDTO {
    private List<Long> patientsIds;
}
