package gr.codehub.teamOne.resource.interfaces;

import gr.codehub.teamOne.exceptions.BadEntityException;
import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.representation.ConsultationDTO;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;

import java.util.List;

public interface ConsultationResource {

    @Get("json")
    List<ConsultationDTO> getConsultation() throws BadEntityException, NotFoundException;

    @Post("json")
    String addConsultation(ConsultationDTO consultationDTO) throws BadEntityException;

    @Put("json")
    String updateConsultation(ConsultationDTO consultationDTO) throws BadEntityException;

    @Delete("json")
    String deleteConsultation() throws BadEntityException;
}
