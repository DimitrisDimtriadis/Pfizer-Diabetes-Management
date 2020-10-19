package gr.codehub.teamOne.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date creationDate;

    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "basket")
    private List<BasketProduct> cartProductList = new ArrayList<>();
}