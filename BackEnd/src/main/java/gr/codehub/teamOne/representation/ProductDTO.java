package gr.codehub.teamOne.representation;

import lombok.Data;

@Data
public class ProductDTO {

    private String name;
    private double price;
    private int inventory;

    private String uri;
}
