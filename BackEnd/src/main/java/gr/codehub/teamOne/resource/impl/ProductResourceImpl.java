package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.representation.ProductDTO;
import gr.codehub.teamOne.resource.ProductResource;
import org.restlet.resource.ServerResource;

public class ProductResourceImpl extends ServerResource implements ProductResource {
    @Override
    public ProductDTO getProduct() throws NotFoundException {
        return null;
    }

    @Override
    public void removeProduct() throws NotFoundException {

    }

    @Override
    public ProductDTO updateProduct(ProductDTO product) throws NotFoundException, BadEntityException {
        return null;
    }
}
