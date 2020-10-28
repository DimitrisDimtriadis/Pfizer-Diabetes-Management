package gr.codehub.teamOne.resource;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.model.Measurement;
import gr.codehub.teamOne.representation.MeasurementDTO;
import gr.codehub.teamOne.representation.PatientDTO;
import org.restlet.resource.*;

import java.util.List;

public interface MeasurementResource {

    @Get("json")
    public List<MeasurementDTO> getMeasurementForUser() throws NotFoundException;

    @Delete
    public void removeMeasurement() throws NotFoundException;

    @Put("json")
    public MeasurementDTO updateMeasurement(MeasurementDTO measurementDTO)
            throws NotFoundException, BadEntityException;

    @Post("json")
    public MeasurementDTO addMeasurement(MeasurementDTO measurementDTO)
            throws NotFoundException, BadEntityException;
}


