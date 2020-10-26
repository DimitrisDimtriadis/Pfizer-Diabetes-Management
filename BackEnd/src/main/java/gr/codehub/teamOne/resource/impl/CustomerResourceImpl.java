package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.Utilities.GeneralFunctions;
import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.model.Customer;
import gr.codehub.teamOne.repository.CustomerRepository;
import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.representation.CustomerDTO;
import gr.codehub.teamOne.resource.CustomerResource;
import gr.codehub.teamOne.resource.util.ResourceUtils;
import gr.codehub.teamOne.security.AccessRole;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.Optional;

public class CustomerResourceImpl extends ServerResource implements CustomerResource {

    private CustomerRepository customerRepository;
    private EntityManager em;
    private long id;

    @Override
    protected void doInit() {

        try {
            em = JpaUtil.getEntityManager();
            customerRepository = new CustomerRepository(em);
            id = Long.parseLong(getAttribute("id"));
        } catch(Exception e){
            throw new ResourceException(e);
        }
    }

    @Override
    protected void doRelease() {
        em.close();
    }

    @Override
    public CustomerDTO getCustomer() throws NotFoundException, ResourceException {

        ResourceUtils.checkRole(this, GeneralFunctions.rolesWithAccess(true, false, true));

        Optional<Customer> customer = customerRepository.findById(id);
        setExisting(customer.isPresent());
        if (!customer.isPresent()) throw new NotFoundException("Customer not found !");
        return CustomerDTO.getCustomerDTO(customer.get());
    }

    @Override
    public void remove() throws NotFoundException {

        ResourceUtils.checkRole(this, GeneralFunctions.rolesWithAccess(false, false, true));
        customerRepository.deletedById(id);
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) throws NotFoundException, BadEntityException {

        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (!customerOpt.isPresent()) throw new NotFoundException("The given customer id is not existing");
        Customer customer = customerOpt.get();

        customer.setName(customerDTO.getName());
        customer.setDob(customerDTO.getDob());
        customer.setAddress(customerDTO.getAddress());
        customer.setCategory(customerDTO.getCategory());

        customerRepository.save(customer);
        return CustomerDTO.getCustomerDTO(customer);
    }
}
