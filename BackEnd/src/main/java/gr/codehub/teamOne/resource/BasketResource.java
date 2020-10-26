package gr.codehub.teamOne.resource;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.representation.BasketDTO;
import org.restlet.resource.*;

public interface BasketResource {

    @Get("json")
    public BasketDTO getBasket() throws NotFoundException;

    @Delete
    public void removeProduct() throws NotFoundException;

    @Put("json")
    public BasketDTO updateBasket(BasketDTO basket) throws NotFoundException, BadEntityException;

    @Post("json")
    public BasketDTO createBasket() throws NotFoundException, BadEntityException;

    @Patch("json")
    public BasketDTO assignCustomer(BasketDTO basketDTO) throws NotFoundException, BadEntityException;
}
