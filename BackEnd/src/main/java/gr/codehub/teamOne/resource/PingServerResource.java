package gr.codehub.teamOne.resource;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class PingServerResource extends ServerResource {

    public static final String PING = "Sacchon Web API v1.0 running";

    /**
     * @return Default text to show that server works
     */
    @Get("txt")
    public String ping() { return PING; }
}
