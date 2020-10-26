package gr.codehub.teamOne.resource;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.representation.UsersDTO;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import java.util.List;

public interface UsersResource {

    @Get("json")
    public List<UsersDTO> getsUsers() throws NotFoundException;

    @Post("json")
    public UsersDTO addUser(UsersDTO usersDTO) throws NotFoundException, BadEntityException;

    @Delete
    public  void removeUser() throws NotFoundException;
}
