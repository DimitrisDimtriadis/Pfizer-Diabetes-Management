package gr.codehub.teamOne.representation;

import gr.codehub.teamOne.model.Basket;
import lombok.Data;

import java.util.Date;

@Data
public class BasketDTO {

    private Date creationDate;
    private Long customerID;
    private Long basketID;
    private String uri;

    static public Basket getBasket(BasketDTO basketDTO) {

        Basket basket = new Basket();

        basket.setCreationDate(basketDTO.getCreationDate());
        return basket;
    }

    static public BasketDTO getBasketDTO(Basket basket) {

        BasketDTO basketDTO = new BasketDTO();
        basketDTO.setCreationDate(basket.getCreationDate());
        basketDTO.setBasketID(basket.getId());

        if (basket.getCustomer() != null) {
            basketDTO.setCustomerID(basket.getCustomer().getId());
        }

        basketDTO.setUri("http://localhost:9000/sacchon/basket/" + basket.getId());
        return basketDTO;
    }
}
