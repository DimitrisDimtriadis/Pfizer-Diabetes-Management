package gr.codehub.teamOne.representation;

import gr.codehub.teamOne.model.Measurements;
import lombok.Data;

import java.util.Date;

@Data
public class MeasurementDTO {

    private Date measurementDate;
    private long patientAMKA;
    private long measurementID;
    private String uri;

    static public Measurements getMeasurements(MeasurementDTO measurementDTO){

        Measurements measurements = new Measurements();
        measurements.setMeasurementDate(measurementDTO.getMeasurementDate());
        return measurements;
    }

    static public MeasurementDTO getMeasurementsRepresentation(Measurements measurements){

        MeasurementDTO measurementDTO = new MeasurementDTO();
        measurementDTO.setMeasurementDate(measurements.getMeasurementDate());

//        if (measurements.getPatients()!=null)
////            measurementDTO.setPatientAMKA(measurements.getPatients().getAMKA());
//            measurementDTO.setMeasurementID(measurements.getId());
//
//           measurementDTO.setUri("http://localhost:9000/measurements/"+measurements.getId());
           return null;//measurementDTO;
    }
}
