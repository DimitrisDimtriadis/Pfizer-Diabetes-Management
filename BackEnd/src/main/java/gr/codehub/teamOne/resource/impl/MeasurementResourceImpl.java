package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.representation.MeasurementDTO;
import gr.codehub.teamOne.representation.PatientDTO;
import gr.codehub.teamOne.resource.MeasurementResource;
import org.restlet.resource.ServerResource;

public class MeasurementResourceImpl extends ServerResource implements MeasurementResource {

    @Override
    public MeasurementDTO getMeasurementForUser() throws NotFoundException {
        return null;
    }

    @Override
    public void removeMeasurement() throws NotFoundException {

    }

    @Override
    public MeasurementDTO updateMeasurement(PatientDTO patientRepresentation) throws NotFoundException, BadEntityException {
        return null;
    }

    @Override
    public MeasurementDTO createMeasurement() throws NotFoundException, BadEntityException {
        return null;
    }
}
