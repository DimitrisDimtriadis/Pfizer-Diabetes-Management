package gr.codehub.teamOne.resource;

import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.representation.UsersDTO;
import org.restlet.resource.Get;

public interface ProfileResource {

    @Get("json")
    UsersDTO getProfileInfo() throws NotFoundException;
}
