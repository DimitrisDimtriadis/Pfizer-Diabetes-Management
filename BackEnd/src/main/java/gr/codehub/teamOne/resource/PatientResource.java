package gr.codehub.teamOne.resource;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.model.Patients;
import gr.codehub.teamOne.representation.PatientRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface PatientResource {

    @Get("json")
    public PatientRepresentation getPatients() throws NotFoundException;

    @Delete
    public void remove() throws NotFoundException;

    @Put("json")
    public Patients update(Patients patients ) throws NotFoundException, BadEntityException;
}
