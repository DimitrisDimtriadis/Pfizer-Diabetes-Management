package gr.codehub.teamOne.router;

import gr.codehub.teamOne.resource.impl.MeasurementResourceImpl;
import gr.codehub.teamOne.resource.impl.PatientResourceImpl;
import gr.codehub.teamOne.resource.impl.PingServerResourceImpl;
import gr.codehub.teamOne.resource.impl.LoginRegisterResourceImpl;
import org.restlet.Application;
import org.restlet.routing.Router;

public class CustomRouter {

    private Application application;

    public CustomRouter(Application application) {
        this.application = application;
    }

    public Router createApiRouter() {

        Router router = new Router(application.getContext());

//        router.attach("/measurements", MeasurementResourceImpl.class);
//        router.attach("/users", LoginRegisterResourceImpl.class);
        router.attach("/patient", PatientResourceImpl.class);

        return router;
    }

    public Router publicResources() {
        Router router = new Router();
        router.attach("/ping", PingServerResourceImpl.class);
        router.attach("/users", LoginRegisterResourceImpl.class);
        router.attach("/measurements", MeasurementResourceImpl.class);
        return router;
    }

}
