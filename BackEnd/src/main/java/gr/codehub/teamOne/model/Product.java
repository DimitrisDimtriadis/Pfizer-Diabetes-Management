package gr.codehub.teamOne.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private double price;
    private int inventoryQuantity;

    @OneToMany(mappedBy = "product")
    private List<BasketProduct> basketProductList = new ArrayList<>();
}
