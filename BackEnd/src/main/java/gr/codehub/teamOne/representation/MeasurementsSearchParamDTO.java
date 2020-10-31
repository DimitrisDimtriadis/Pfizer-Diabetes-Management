package gr.codehub.teamOne.representation;

import lombok.Data;

import java.util.Date;

@Data
public class MeasurementsSearchParamDTO {
    private Long userID;
    private Date startAt;
    private Date endAt;
}
