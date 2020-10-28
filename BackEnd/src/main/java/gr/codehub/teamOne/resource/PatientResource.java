package gr.codehub.teamOne.resource;

import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.representation.PatientDTO;
import gr.codehub.teamOne.representation.UsersDTO;
import org.restlet.resource.Get;

import java.util.List;

public interface PatientResource {

    @Get("json")
    public List<PatientDTO> getsPatients() throws NotFoundException;

}
