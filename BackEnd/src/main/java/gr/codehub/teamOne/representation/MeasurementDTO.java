package gr.codehub.teamOne.representation;

import gr.codehub.teamOne.model.Measurement;
import lombok.Data;

import java.util.Date;

@Data
public class MeasurementDTO {

    private long user;
    private float bloodGlucoseLevel;
    private long carbIntake;
    private Date measurementDate;

    /**
     * Convert object from measurementDTO to measurement
     *
     * @param measurementDTO Object measurementDTO (for front)
     * @return Object measurement (for base)
     */
    static public Measurement getMeasurement(MeasurementDTO measurementDTO) {

        Measurement measurement = new Measurement();
        measurement.setBloodGlucoseLevel(measurementDTO.getBloodGlucoseLevel());
        measurement.setCarbIntake(measurementDTO.getCarbIntake());
        measurement.setMeasurementDate(new Date());
        return measurement;
    }

    /**
     * Convert object from measurement to measurementDTO
     *
     * @param measurement  Object measurement (for base)
     * @return Object measurementDTO (for front)
     */
    static public MeasurementDTO getMeasurementDTO(Measurement measurement) {

        MeasurementDTO measurementDTO = new MeasurementDTO();
        measurementDTO.setUser(measurement.getUser().getId());
        measurementDTO.setBloodGlucoseLevel(measurement.getBloodGlucoseLevel());
        measurementDTO.setCarbIntake(measurement.getCarbIntake());
        measurementDTO.setMeasurementDate(measurement.getMeasurementDate());
        return measurementDTO;
    }
}
