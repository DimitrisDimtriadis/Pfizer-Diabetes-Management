package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.model.Book;
import gr.codehub.teamOne.resource.PingResource;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import static java.lang.System.*;

public class PingServerResource extends ServerResource implements PingResource {

   @Override
    public  String ping(){

        return "hello the server is running....";

    };



}
