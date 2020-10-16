package gr.codehub.teamOne.resource;

import org.restlet.resource.Get;

public interface PingResource {
    @Get("json")
    public  String ping();
}
