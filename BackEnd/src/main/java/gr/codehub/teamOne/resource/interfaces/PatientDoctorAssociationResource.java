package gr.codehub.teamOne.resource.interfaces;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.exceptions.WrongUserRoleException;
import gr.codehub.teamOne.representation.PatientDoctorAssociationDTO;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;

import java.util.List;

public interface PatientDoctorAssociationResource {

    @Get("json")
    List<PatientDoctorAssociationDTO> getAllAssociations() throws BadEntityException;

    @Put("json")
    PatientDoctorAssociationDTO addNewAssociation(PatientDoctorAssociationDTO newAssociationDTO) throws BadEntityException, WrongUserRoleException, NotFoundException;
}
