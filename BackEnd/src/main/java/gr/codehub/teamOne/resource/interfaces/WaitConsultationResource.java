package gr.codehub.teamOne.resource.interfaces;

import gr.codehub.teamOne.representation.UsersDTO;
import org.restlet.resource.Get;

import java.util.List;

public interface WaitConsultationResource {

    @Get("json")
    List<UsersDTO> getPatientsWaiting();
}
