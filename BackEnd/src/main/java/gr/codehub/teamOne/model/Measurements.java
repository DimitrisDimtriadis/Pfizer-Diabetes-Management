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
public class Measurements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //blood glucose level (date, time, measured in mg/dL)
    private long bloodGlycoseLevel;

    //carb intake (measured in grams)
    private long carbIntake;

    private Date measurmentDate;

    @ManyToOne
    private Doctors doctors;


    @OneToMany(mappedBy = "measuraments")
    private List<PatientMeasuraments> patientMeasuraments=new ArrayList<>();





}
