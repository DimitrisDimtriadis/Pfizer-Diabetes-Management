package gr.codehub.teamOne.representation;

import gr.codehub.teamOne.model.Measurements;
import lombok.Data;

import java.util.Date;

@Data
public class MeasurementsRepresentation {

    private Date measurementDate;
    private long patientAMKA;
    private long measurementID;
    private String uri;

    static public Measurements getMeasurements(MeasurementsRepresentation measurementsRepresentation){

        Measurements measurements = new Measurements();
        measurements.setMeasurementDate(measurementsRepresentation.getMeasurementDate());
        return measurements;
    }

    static public MeasurementsRepresentation getMeasurementsRepresentation(Measurements measurements){

        MeasurementsRepresentation measurementsRepresentation = new MeasurementsRepresentation();
        measurementsRepresentation.setMeasurementDate(measurements.getMeasurementDate());

        if (measurements.getPatients()!=null)
            measurementsRepresentation.setPatientAMKA(measurements.getPatients().getAMKA());
            measurementsRepresentation.setMeasurementID(measurements.getId());

           measurementsRepresentation.setUri("http://localhost:9000/measurements/"+measurements.getId());
           return measurementsRepresentation;
    }
}
