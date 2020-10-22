package gr.codehub.teamOne.resource;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.representation.CustomerDTO;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface CustomerResource {

    @Get("json")
    public CustomerDTO getCustomer() throws NotFoundException;

    @Delete
    public void remove() throws NotFoundException;

    @Put("json")
    public CustomerDTO update(CustomerDTO customerDTO) throws NotFoundException, BadEntityException;
}
