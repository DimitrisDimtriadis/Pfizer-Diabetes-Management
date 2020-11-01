package gr.codehub.teamOne.resource.util;

import gr.codehub.teamOne.representation.DoctorExpiredDTO;
import gr.codehub.teamOne.security.AccessRole;
import org.restlet.resource.Get;

import java.util.List;

public interface DoctorExpiredResourc {


    @Get("json")
    List<DoctorExpiredDTO> showDoctorExpired(AccessRole accessRole);
}
