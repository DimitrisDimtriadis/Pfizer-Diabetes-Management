package gr.codehub;

import gr.codehub.MediDataRepo.util.JpaUtil;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;

import javax.persistence.EntityManager;
import java.util.logging.Logger;

public class SacchonApp extends Application {
    public static final Logger LOGGER= Engine.getLogger(SacchonApp.class);

    public static void main(String[] args) {
        LOGGER.info("Server is starting......");

        EntityManager em = JpaUtil.getEntityManager();
        em.close();




    }
}
