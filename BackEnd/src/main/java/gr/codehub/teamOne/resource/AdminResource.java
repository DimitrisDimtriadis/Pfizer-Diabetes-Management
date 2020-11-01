package gr.codehub.teamOne.resource;

import gr.codehub.teamOne.exceptions.NotFoundException;
import gr.codehub.teamOne.security.AccessRole;
import org.restlet.resource.Get;

import java.util.List;

public interface AdminResource {
    @Get("json")
    public List checkDoctorOfflinePeriod(AccessRole accessRole) throws NotFoundException;
}
