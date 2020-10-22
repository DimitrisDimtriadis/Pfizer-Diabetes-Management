package gr.codehub.teamOne;

import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.router.CustomRouter;
import gr.codehub.teamOne.security.Shield;
import gr.codehub.teamOne.security.cors.CustomCorsFilter;
import gr.codehub.teamOne.security.dao.AccessRole;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.Role;

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
        CustomRouter customRouter = new CustomRouter(this);
        Shield shield = new Shield(this);

        ChallengeAuthenticator apiGuard = shield.createApiGuard();

        Router publicRouter = customRouter.createApiRouter();
        Router apiRouter = customRouter.createApiRouter();

        apiGuard.setNext(apiRouter);
        publicRouter.attachDefault(apiGuard);

        CustomCorsFilter corsFilter = new CustomCorsFilter(this);
        return corsFilter.createCorsFilter(publicRouter);
    }

    private static void startHibernate(){
        LOGGER.info("Starting with hibernate");

        EntityManager em = JpaUtil.getEntityManager();
        em.close();
    }

    public RestApplication(){
        setName("WebApiTutorial");
        setDescription("Full web API Tutorial");

        getRoles().add(new Role(this, AccessRole.ROLE_ADMIN.getRoleName()));
        getRoles().add(new Role(this, AccessRole.ROLE_OWNER.getRoleName()));
        getRoles().add(new Role(this, AccessRole.ROLE_USER.getRoleName()));
    }

    //All about Rest Service
    private static void startRestService(){
        LOGGER.info("Contacts application starting...");

        // Attach application to http://localhost:9000/sacchon
        Component c = new Component();
        c.getServers().add(Protocol.HTTP, 9000);
        c.getDefaultHost().attach("/sacchon", new RestApplication());

        try {
            c.start();
        } catch (Exception e){
            LOGGER.info("Something went wrong with starting of Rest Service. Error: " + e.getMessage());
        }
        LOGGER.info("Sample Web API started");
        LOGGER.info("URL: http://localhost:9000/sacchon/customer/1");
    }
    // next 
}
