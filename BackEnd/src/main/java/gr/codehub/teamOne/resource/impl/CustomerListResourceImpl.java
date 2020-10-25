package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.model.Customer;
import gr.codehub.teamOne.repository.CustomerRepository;
import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.representation.CustomerDTO;
import gr.codehub.teamOne.resource.CustomerListResource;
import gr.codehub.teamOne.resource.util.ResourceUtils;
import gr.codehub.teamOne.security.AccessRole;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class CustomerListResourceImpl extends ServerResource implements CustomerListResource {

    private CustomerRepository customerRepository;
    private EntityManager em;

    @Override
    protected void doInit() {
        try{
            em = JpaUtil.getEntityManager();
            customerRepository = new CustomerRepository(em);
        }catch(Exception ex){
            throw new ResourceException(ex);
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public CustomerDTO add(CustomerDTO customerIn) throws BadEntityException, ResourceException {

        ResourceUtils.checkRole(this, AccessRole.ROLE_USER.getRoleName());
        if(customerIn == null) throw new BadEntityException("Null customer representation error");
        if(customerIn.getName().equals("admin")) throw new BadEntityException("Invalid customer name error");

        Customer customer = CustomerDTO.getCustomer(customerIn);
        customerRepository.save(customer);
        return  CustomerDTO.getCustomerDTO(customer);
    }

    @Override
    public List<CustomerDTO> getCustomers() throws NotFoundException {
        ResourceUtils.checkRole(this, AccessRole.ROLE_USER.getRoleName());
        List<Customer> customers= customerRepository.findAll();

        List<CustomerDTO> customerRepresentationList = new ArrayList<>();

        customers.forEach( customer -> customerRepresentationList.add(CustomerDTO.getCustomerDTO(customer)));

        return customerRepresentationList;
    }
}
