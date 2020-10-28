package gr.codehub.teamOne.resource;

import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.representation.DoctorDTO;

import org.restlet.resource.Get;

import java.util.List;

public interface DoctorResource  {
    @Get("json")
    public List<DoctorDTO> getsDoctors() throws NotFoundException;

}

