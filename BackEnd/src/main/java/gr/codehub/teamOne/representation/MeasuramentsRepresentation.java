package gr.codehub.teamOne.representation;

import gr.codehub.teamOne.model.Measurements;
import lombok.Data;

import java.util.Date;

@Data
public class MeasuramentsRepresentation {

    private Date measurementDate;
    private long patientAmka;
    private long measuramentId;
    private String uri;


    static public Measurements getMeasurements(MeasuramentsRepresentation measurementsRepresentation){
        Measurements measurements = new Measurements();

        measurements.setMeasurmentDate(measurementsRepresentation.getMeasurementDate());


        return measurements;
    }

    static public MeasuramentsRepresentation getmeasurementsRepresentation(Measurements measurements){
        MeasuramentsRepresentation measurementsRepresentation = new MeasuramentsRepresentation();
        measurementsRepresentation.setMeasurementDate(measurements.getMeasurmentDate());

        if (measurements.getPatients()!=null)
            measurementsRepresentation.setPatientAmka(measurements.getPatients().getAmka());
            measurementsRepresentation.setMeasuramentId(measurements.getId());

           measurementsRepresentation.setUri("http://localhost:9000/measurements/"+measurements.getId());
           return measurementsRepresentation;
    }

}
