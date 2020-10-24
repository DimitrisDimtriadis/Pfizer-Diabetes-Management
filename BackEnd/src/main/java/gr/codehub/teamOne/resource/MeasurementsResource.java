package gr.codehub.teamOne.resource;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.representation.MeasuramentsRepresentation;
import gr.codehub.teamOne.representation.PatientRepresentation;
import org.restlet.resource.*;

public interface MeasurementsResource {

    @Get("json")
    public MeasuramentsRepresentation getBasket() throws NotFoundException;

    @Delete
    public void removeMeasurements() throws NotFoundException;

    @Put("json")
    public  MeasuramentsRepresentation  updateMeasurements(PatientRepresentation patientRepresentation)
            throws NotFoundException, BadEntityException;

   // @Post("json")
   // public  MeasuramentsRepresentation  createMeasuraments()
    //        throws NotFoundException, BadEntityException;


    @Patch("json")
    public  MeasuramentsRepresentation  assignPatient( MeasuramentsRepresentation   measuramentsRepresentation )
            throws NotFoundException, BadEntityException;
}


