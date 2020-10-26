package gr.codehub.teamOne.resource;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.representation.ProductDTO;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface ProductResource {

    @Get("json")
    public ProductDTO getProduct() throws NotFoundException;

    @Delete("json")
    public void removeProduct() throws NotFoundException;

    @Put("json")
    public ProductDTO updateProduct(ProductDTO product) throws NotFoundException, BadEntityException;
}
