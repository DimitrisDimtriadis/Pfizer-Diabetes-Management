package gr.codehub.teamOne.resource;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.representation.MeasurementDTO;
import gr.codehub.teamOne.representation.PatientDTO;
import org.restlet.resource.*;

public interface MeasurementResource {

    @Get("json")
    public MeasurementDTO getMeasurementForUser() throws NotFoundException;

    @Delete
    public void removeMeasurement() throws NotFoundException;

    @Put("json")
    public MeasurementDTO updateMeasurement(PatientDTO patientRepresentation)
            throws NotFoundException, BadEntityException;

    @Post("json")
    public MeasurementDTO createMeasurement()
            throws NotFoundException, BadEntityException;
}


