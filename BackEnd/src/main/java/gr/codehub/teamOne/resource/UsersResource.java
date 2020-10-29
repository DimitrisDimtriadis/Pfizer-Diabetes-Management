package gr.codehub.teamOne.resource;

import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.representation.UsersDTO;
import org.restlet.resource.Post;

public interface UsersResource {

    @Post("json")
    public UsersDTO findUserByAmka(UsersDTO usersDTO) throws NotFoundException;
}
