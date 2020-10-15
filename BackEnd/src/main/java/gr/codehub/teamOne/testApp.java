package gr.codehub.teamOne;

import gr.codehub.teamOne.resource.impl.BookListServerResource;
import gr.codehub.teamOne.resource.impl.BookServerResource;
import gr.codehub.teamOne.resource.impl.PingServerResource;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;

import org.restlet.routing.Router;

import java.util.logging.Logger;

public class testApp extends Application {
    public static final Logger LOGGER = Engine.getLogger(testApp.class);

    public static void main(String[] args) throws Exception {
        LOGGER.info("Contacts application starting...");


        // Attach application to http://localhost:9000/v1
        Component c = new Component();
        c.getServers().add(Protocol.HTTP, 9000);


        c.getDefaultHost().attach("/v1", new testApp());

        c.start();
        LOGGER.info("Sample Web API started");
        LOGGER.info("URL: http://localhost:9000/v1/ping");
    }

    @Override
    public Restlet createInboundRoot() {

        Router publicRouter = publicResources();

        // Create the api router, protected by a guard

        return publicRouter;
    }


    public Router publicResources() {
        Router router = new Router();

        router.attach("/ping", PingServerResource.class);
        router.attach("/book", BookServerResource.class);
        router.attach("/books", BookListServerResource.class);

        return router;
    }

}
