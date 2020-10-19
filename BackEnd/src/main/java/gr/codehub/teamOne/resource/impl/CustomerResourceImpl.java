package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.model.Customer;
import gr.codehub.teamOne.repository.CustomerRepository;
import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.representation.CustomerDTO;
import gr.codehub.teamOne.resource.CustomerResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.Optional;

public class CustomerResourceImpl extends ServerResource implements CustomerResource {

    private CustomerRepository customerRepository;
    private EntityManager em;
    private long id;

    @Override
    protected void doInit() throws ResourceException {

        try {
            em = JpaUtil.getEntityManager();
            customerRepository = new CustomerRepository(em);
            id = Long.parseLong(getAttribute("id"));
        } catch(Exception e){
            throw new ResourceException(e);
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public CustomerDTO getCustomer() throws NotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        setExisting(customer.isPresent());
        if (!customer.isPresent()) throw new NotFoundException("Customer not found !");
        CustomerDTO customerDTO = CustomerDTO.getCustomer(customer.get());
        return customerDTO;
    }

    @Override
    public void remove() throws NotFoundException {

    }

    @Override
    public CustomerDTO store(CustomerDTO customerDTO) throws NotFoundException, BadEntityException {
        return null;
    }
}
