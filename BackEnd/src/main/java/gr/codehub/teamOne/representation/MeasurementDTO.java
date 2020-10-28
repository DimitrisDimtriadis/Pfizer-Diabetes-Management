package gr.codehub.teamOne.representation;

import gr.codehub.teamOne.model.Measurement;
import lombok.Data;

import java.util.Date;

@Data
public class MeasurementDTO {

    private Date measurementDate;

    static public Measurement getMeasurement(MeasurementDTO measurementDTO){

        Measurement measurement = new Measurement();
        measurement.setMeasurementDate(measurementDTO.getMeasurementDate());
        return measurement;
    }

    static public MeasurementDTO getMeasurementsRepresentation(Measurement measurement){

        MeasurementDTO measurementDTO = new MeasurementDTO();
        measurementDTO.setMeasurementDate(measurement.getMeasurementDate());

//        if (measurement.getPatients()!=null)
////            measurementDTO.setPatientAMKA(measurement.getPatients().getAMKA());
//            measurementDTO.setMeasurementID(measurement.getId());
//
//           measurementDTO.setUri("http://localhost:9000/measurements/"+measurement.getId());
           return null;//measurementDTO;
    }
}
