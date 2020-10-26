package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.Utilities.GeneralFunctions;
import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.model.Basket;
import gr.codehub.teamOne.model.Customer;
import gr.codehub.teamOne.repository.BasketRepository;
import gr.codehub.teamOne.repository.CustomerRepository;
import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.representation.BasketDTO;
import gr.codehub.teamOne.resource.BasketResource;
import gr.codehub.teamOne.resource.util.ResourceUtils;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.Date;

public class BasketResourceImpl extends ServerResource implements BasketResource {

    private BasketRepository basketRepository;
    private CustomerRepository customerRepository;
    private EntityManager em;

    @Override
    protected void doInit() throws ResourceException {
        try {
            em = JpaUtil.getEntityManager();
            customerRepository = new CustomerRepository(em);
            basketRepository = new BasketRepository(em);
        } catch (Exception e) {
            throw new ResourceException(e);
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public BasketDTO getBasket() throws NotFoundException {
        return null;
    }

    @Override
    public void removeProduct() throws NotFoundException {

    }

    @Override
    public BasketDTO updateBasket(BasketDTO basket) throws NotFoundException, BadEntityException {
        return null;
    }

    @Override
    public BasketDTO createBasket() throws NotFoundException, BadEntityException {

        ResourceUtils.checkRole(this, GeneralFunctions.rolesWithAccess(true, false, true));

        Basket basket = new Basket();
        basket.setCreationDate(new Date());
        basketRepository.save(basket);
        return BasketDTO.getBasketDTO(basket);
    }

    @Override
    public BasketDTO assignCustomer(BasketDTO basketDTO) throws NotFoundException, BadEntityException {

        if (basketDTO == null) throw new NotFoundException("Not found DTO");
        Customer customerFromDB = customerRepository.findById(basketDTO.getCustomerID()).get();
        Basket basketFromDB = basketRepository.findById(basketDTO.getBasketID()).get();

        basketFromDB.setCustomer(customerFromDB);
        basketRepository.save(basketFromDB);

        return BasketDTO.getBasketDTO(basketFromDB);
    }
}
