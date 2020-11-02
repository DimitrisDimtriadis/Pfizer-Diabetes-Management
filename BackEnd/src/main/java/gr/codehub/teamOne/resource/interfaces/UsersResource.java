package gr.codehub.teamOne.resource.interfaces;

import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.exceptions.WrongUserRoleException;
import gr.codehub.teamOne.representation.UsersDTO;
import gr.codehub.teamOne.representation.UsersSearchDTO;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

public interface UsersResource {
    @Get("json")
    public UsersDTO findUserById(UsersSearchDTO usersSearchDTO)throws NotFoundException;
    @Post("json")
    public UsersDTO findUserByAmka(UsersSearchDTO usersSearchDTO) throws NotFoundException;

    @Delete
    String removeUser() throws NotFoundException, WrongUserRoleException;
}
