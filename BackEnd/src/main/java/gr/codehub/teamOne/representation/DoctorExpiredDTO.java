package gr.codehub.teamOne.representation;

import gr.codehub.teamOne.model.Users;
import lombok.Data;

@Data
public class DoctorExpiredDTO {
    private int doctorId;
    private String name;



    public static DoctorExpiredDTO getDocExpiredLog(Users users){

        DoctorExpiredDTO doctorExpiredDTO=new DoctorExpiredDTO();
        doctorExpiredDTO.setName(users.getFirst_name());
        doctorExpiredDTO.setDoctorId((int) users.getId());

        return doctorExpiredDTO;


    }
}

