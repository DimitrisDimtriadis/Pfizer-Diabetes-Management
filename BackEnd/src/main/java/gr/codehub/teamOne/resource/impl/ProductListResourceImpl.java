package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.representation.ProductDTO;
import gr.codehub.teamOne.resource.ProductListResource;
import org.restlet.resource.ServerResource;

import java.util.List;

public class ProductListResourceImpl extends ServerResource implements ProductListResource {

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) throws BadEntityException {
        return null;
    }

    @Override
    public List<ProductDTO> getProducts() throws NotFoundException {
        return null;
    }

}
