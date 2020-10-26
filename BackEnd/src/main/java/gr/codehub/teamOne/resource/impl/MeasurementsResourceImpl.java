package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.representation.MeasurementsRepresentation;
import gr.codehub.teamOne.representation.PatientRepresentation;
import gr.codehub.teamOne.resource.MeasurementsResource;
import org.restlet.resource.ServerResource;

public class MeasurementsResourceImpl extends ServerResource implements MeasurementsResource {
    @Override
    public MeasurementsRepresentation getBasket() throws NotFoundException {
        return null;
    }

    @Override
    public void removeMeasurements() throws NotFoundException {

    }

    @Override
    public MeasurementsRepresentation updateMeasurements(PatientRepresentation patientRepresentation) throws NotFoundException, BadEntityException {
        return null;
    }

    @Override
    public MeasurementsRepresentation createMeasurements() throws NotFoundException, BadEntityException {
        return null;
    }

    @Override
    public MeasurementsRepresentation assignPatient(MeasurementsRepresentation measurementsRepresentation) throws NotFoundException, BadEntityException {
        return null;
    }
}
