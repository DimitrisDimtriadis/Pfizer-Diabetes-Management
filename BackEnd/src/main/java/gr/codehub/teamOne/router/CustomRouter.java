package gr.codehub.teamOne.router;

import gr.codehub.teamOne.model.Measurements;
import gr.codehub.teamOne.resource.PingServerResource;
import gr.codehub.teamOne.resource.impl.*;
import org.restlet.Application;
import org.restlet.routing.Router;

public class CustomRouter {

    private Application application;

    public CustomRouter(Application application) {
        this.application = application;
    }

    public Router createApiRouter(){

        Router router = new Router(application.getContext());
        router.attach("/patient/{id}", PatientResourceImpl.class);
        router.attach("/patient", PatientListResourceImpl.class);
        router.attach("/patient/", PatientListResourceImpl.class);

        router.attach("/measuraments", MeasurementsResourceImpl.class);


        return router;
    }

    public Router publicResources(){
        Router router = new Router();
        router.attach("/ping", PingServerResource.class);
        return router;
    }
}
