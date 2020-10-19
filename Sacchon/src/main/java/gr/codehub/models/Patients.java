package gr.codehub.models;

import gr.codehub.gender.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class Patients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long amka;

    private String name;
    private String address;
    private Date dob;
     private Gender gender;

     @OneToMany
     private PatientMeasuraments patientMeasuraments;

     @ManyToOne
    private Doctors doctors;










}
