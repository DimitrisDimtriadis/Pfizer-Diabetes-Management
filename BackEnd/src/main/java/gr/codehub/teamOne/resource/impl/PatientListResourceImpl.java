package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.model.Patients;
import gr.codehub.teamOne.resource.PatientResource;
import org.restlet.resource.ServerResource;

public class PatientListResourceImpl extends ServerResource implements PatientResource {
    @Override
    public Patients getPatients() throws NotFoundException {
        return null;
    }

    @Override
    public void remove() throws NotFoundException {

    }

    @Override
    public Patients update(Patients patients) throws NotFoundException, BadEntityException {
        return null;
    }
}