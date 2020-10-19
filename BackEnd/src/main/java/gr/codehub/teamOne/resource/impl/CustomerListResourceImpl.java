package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.representation.CustomerDTO;
import gr.codehub.teamOne.resource.CustomerListResource;
import org.restlet.resource.ServerResource;

import java.util.List;

public class CustomerListResourceImpl extends ServerResource implements CustomerListResource {

    @Override
    public CustomerDTO add(CustomerDTO customerIn) throws BadEntityException {
        return null;
    }

    @Override
    public List<CustomerDTO> getCustomers() throws NotFoundException {
        return null;
    }
}
