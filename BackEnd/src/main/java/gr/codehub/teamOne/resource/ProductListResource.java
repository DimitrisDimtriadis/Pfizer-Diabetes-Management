package gr.codehub.teamOne.resource;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.representation.ProductDTO;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import java.util.List;

public interface ProductListResource {

    @Post("json")
    public ProductDTO addProduct(ProductDTO productDTO) throws BadEntityException;

    @Get("json")
    public List<ProductDTO> getProducts() throws NotFoundException;

}

