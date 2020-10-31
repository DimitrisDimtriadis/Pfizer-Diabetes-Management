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
public class PatientDoctorAssociation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(optional = false)
//    @Column(unique = true)
    private Users patientID;

    @ManyToOne
    private Users doctorID;

    private Date lastConsulate;
}
