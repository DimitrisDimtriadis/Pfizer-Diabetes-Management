package gr.codehub.teamOne;

import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.router.CustomRouter;
import gr.codehub.teamOne.security.Shield;
import gr.codehub.teamOne.security.cors.CustomCorsFilter;
import gr.codehub.teamOne.security.AccessRole;
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

public class SacchonApp extends Application {

    public static final Logger LOGGER = Engine.getLogger(SacchonApp.class);

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
        CustomCorsFilter corsFilter = new CustomCorsFilter(this);


        // Create the api router, protected by a guard
        Router publicRouter = customRouter.publicResources();
        Router apiRouter = customRouter.createApiRouter();

        publicRouter.attachDefault(apiGuard);
        apiGuard.setNext(apiRouter);

        return corsFilter.createCorsFilter(publicRouter);
    }

    private static void startHibernate() {

        LOGGER.info("Starting with hibernate");

        EntityManager em = JpaUtil.getEntityManager();
        em.close();
    }

    public SacchonApp() {

        setName("Sacchon web Api");
        setDescription("Full web API for Sacchon project");

        getRoles().add(new Role(this, AccessRole.ROLE_ADMIN.getRoleName()));
        getRoles().add(new Role(this, AccessRole.ROLE_DOCTOR.getRoleName()));
        getRoles().add(new Role(this, AccessRole.ROLE_PATIENT.getRoleName()));
    }

    //All about Rest Service
    private static void startRestService() {

        LOGGER.info("Contacts application starting...");

        // Attach application to http://localhost:9000/sacchon
        Component c = new Component();
        c.getServers().add(Protocol.HTTP, 9000);
        c.getDefaultHost().attach("/sacchon", new SacchonApp());

        try {
            c.start();
            LOGGER.info("Sacchon Web API started !");
            LOGGER.info("URL: http://localhost:9000/sacchon/ping");

        } catch (Exception e) {
            LOGGER.info("Something went wrong with starting of Rest Service. Error: " + e.getMessage());
        }
    }
}

//TODO: Gia enan astheni oles tis metrisis kai enan meso oro (kathimerina kai miniaia carb and glosure)
//TODO: Reservations -> Gnomateusi giatrou
//TODO: Visibilite gia to delete
//TODO: Doc poious den exei kanei consult
//TODO: Doc to na stelnei consult
//TODO: Kane elenxo poio asthenis den exoun consult meta apo 30 meres
//TODO: O asthenis na ginetai availble otan o doc feugei
//TODO: An o astheneis exei gnomateusei na eidopoieitai
//TODO: Admin kataxorisis asthenei
//TODO: Admin gnomateuseis doc
//TODO: List without assignment
//TODO: List without entries
//TODO: Check if doc is offline for long time ===>  change login procedure