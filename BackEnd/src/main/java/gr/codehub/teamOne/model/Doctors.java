package gr.codehub.teamOne.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Doctors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String email;
    private String address;

    @OneToMany(mappedBy = "doctors")
    private List<Patients> patients = new ArrayList<>();
}