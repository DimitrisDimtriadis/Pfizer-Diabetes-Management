package gr.codehub.teamOne;

import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.router.CustomRouter;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;

import javax.persistence.EntityManager;
import java.util.logging.Logger;

public class RestApplication extends Application {

    public static final Logger LOGGER = Engine.getLogger(RestApplication.class);

    public static void main(String[] args) throws Exception {
        startHibernate();
        startRestService();
    }

    @Override
    public Restlet createInboundRoot() {

        // Create the api router, protected by a guard
        CustomRouter publicRouter = new CustomRouter(this);
        return publicRouter.createApiRouter();
    }

    private static void startHibernate(){
        LOGGER.info("Starting with hibernate");

        EntityManager em = JpaUtil.getEntityManager();
        em.close();
    }

    //All about Rest Service
    private static void startRestService(){
        LOGGER.info("Contacts application starting...");

        // Attach application to http://localhost:9000/v1
        Component c = new Component();
        c.getServers().add(Protocol.HTTP, 9000);
        c.getDefaultHost().attach("/v1", new RestApplication());

        try {
            c.start();
        } catch (Exception e){
            LOGGER.info("Something went wrong with starting of Rest Service. Error: " + e.getMessage());
        }
        LOGGER.info("Sample Web API started");
        LOGGER.info("URL: http://localhost:9000/v1/customer/1");
    }
}
