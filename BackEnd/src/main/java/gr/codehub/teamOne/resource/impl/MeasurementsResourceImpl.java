package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.representation.MeasuramentsRepresentation;
import gr.codehub.teamOne.representation.PatientRepresentation;
import gr.codehub.teamOne.resource.MeasurementsResource;
import org.restlet.resource.ServerResource;

public class MeasurementsResourceImpl extends ServerResource implements MeasurementsResource {
    @Override
    public MeasuramentsRepresentation getBasket() throws NotFoundException {
        return null;
    }

    @Override
    public void removeMeasurements() throws NotFoundException {

    }

    @Override
    public MeasuramentsRepresentation updateMeasurements(PatientRepresentation patientRepresentation) throws NotFoundException, BadEntityException {
        return null;
    }

    @Override
    public MeasuramentsRepresentation assignPatient(MeasuramentsRepresentation measuramentsRepresentation) throws NotFoundException, BadEntityException {
        return null;
    }
}
