package gr.codehub.teamOne.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"patient_id"})})

public class PatientDoctorAssociation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(optional = false)
    private Users patient;

    @ManyToOne
    private Users doctor;

    private boolean isActive;
<<<<<<< HEAD
}
=======
}
>>>>>>> 2ac8e3d15dbed2052cfd72b0df639bc505bbc555
