package gr.codehub.teamOne.resource.util;

import org.restlet.data.Status;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class ResourceUtils {
    public static void checkRole(ServerResource serverResource, String role) throws ResourceException {
        if(!serverResource.isInRole(role)){
            throw  new ResourceException(Status.CLIENT_ERROR_FORBIDDEN.getCode(),"You 're not authorize to send this call.");
        }
    }

}
