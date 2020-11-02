package gr.codehub.teamOne.resource.interfaces;

import gr.codehub.teamOne.representation.MeasurementsSearchParamDTO;
import org.restlet.resource.Post;

public interface DataResource {

    @Post("json")
    String getAvgData(MeasurementsSearchParamDTO measurementsSearchParamDTO);
}
