package gr.codehub.teamOne.resource;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.model.Measurement;
import gr.codehub.teamOne.representation.DeleteMeasurementDTO;
import gr.codehub.teamOne.representation.MeasurementDTO;
import gr.codehub.teamOne.representation.PatientDTO;
import org.restlet.resource.*;

import java.util.List;

public interface MeasurementResource {

    @Get("json")
    List<MeasurementDTO> getMeasurementForUser() throws NotFoundException;

    @Delete
    void removeMeasurement(DeleteMeasurementDTO deleteMeasurementDTO) throws NotFoundException, BadEntityException;

    @Put("json")
    MeasurementDTO updateMeasurement(MeasurementDTO measurementDTO)
            throws NotFoundException, BadEntityException;

    @Post("json")
    String addMeasurement(MeasurementDTO measurementDTO)
            throws NotFoundException, BadEntityException;
}

