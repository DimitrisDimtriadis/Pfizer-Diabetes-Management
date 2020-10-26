package gr.codehub.teamOne.resource;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.representation.MeasurementsRepresentation;
import gr.codehub.teamOne.representation.PatientRepresentation;
import org.restlet.resource.*;

public interface MeasurementsResource {

    @Get("json")
    public MeasurementsRepresentation getBasket() throws NotFoundException;

    @Delete
    public void removeMeasurements() throws NotFoundException;

    @Put("json")
    public MeasurementsRepresentation updateMeasurements(PatientRepresentation patientRepresentation)
            throws NotFoundException, BadEntityException;

    @Post("json")
    public MeasurementsRepresentation createMeasurements()
            throws NotFoundException, BadEntityException;

    @Patch("json")
    public MeasurementsRepresentation assignPatient(MeasurementsRepresentation measurementsRepresentation)
            throws NotFoundException, BadEntityException;
}


