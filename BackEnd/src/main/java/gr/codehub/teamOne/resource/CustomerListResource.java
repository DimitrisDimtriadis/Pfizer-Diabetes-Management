package gr.codehub.teamOne.resource;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.representation.CustomerDTO;
import org.restlet.resource.Get;

import java.util.List;

public interface CustomerListResource {
    @Get("json")
    public CustomerDTO add(CustomerDTO customerIn) throws BadEntityException;

    @Get("json")
    public List<CustomerDTO> getCustomers() throws NotFoundException;
}
