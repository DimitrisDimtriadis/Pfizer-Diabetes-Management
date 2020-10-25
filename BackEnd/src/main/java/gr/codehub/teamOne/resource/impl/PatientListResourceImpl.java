package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.model.Patients;
import gr.codehub.teamOne.repository.PatientRepository;
import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.representation.PatientRepresentation;
import gr.codehub.teamOne.resource.PatientResource;
import gr.codehub.teamOne.resource.utill.ResourceUtils;
import gr.codehub.teamOne.security.dao.AccessRole;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientListResourceImpl extends ServerResource implements PatientResource {


    private PatientRepository patientRepository;
    private EntityManager em;
    private long id;
    private boolean hasId;
    @Override
    protected void doInit() {
        try {
            em = JpaUtil.getEntityManager();
            patientRepository = new PatientRepository(em);
            id = Long.parseLong(getAttribute("id"));
        } catch (Exception ex) {
            throw new ResourceException(ex);
        }

    }

    @Override
    protected void doRelease() {
        em.close();
    }


    @Override
    public PatientRepresentation getPatients() throws NotFoundException {

        List<String> roles = new ArrayList<>();
        roles.add(AccessRole.ROLE_ADMIN.getRoleName());
        roles.add(AccessRole.ROLE_PATIENT.getRoleName());

        ResourceUtils.checkRole(this, roles);
        Optional<Patients> patient = patientRepository.findById(id);
        setExisting(patient.isPresent());
        if (!patient.isPresent()) throw new NotFoundException("Customer is not found");
        PatientRepresentation patientRepresentation = PatientRepresentation.getPatientRepresentation(patient.get());

        return patientRepresentation;

    }

    @Override
    public void remove() throws NotFoundException {
        List<String> roles = new ArrayList<>();
        roles.add(AccessRole.ROLE_ADMIN.getRoleName());

        ResourceUtils.checkRole(this, roles);
        patientRepository.deleteById(id);

    }

    @Override
    public Patients update(Patients patients) throws NotFoundException, BadEntityException {
        return null;
    }
}
