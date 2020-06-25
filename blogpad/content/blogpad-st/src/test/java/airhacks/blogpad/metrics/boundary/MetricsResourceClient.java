package airhacks.blogpad.metrics.boundary;

import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Produces(MediaType.APPLICATION_JSON)
@Path("metrics")
public interface MetricsResourceClient {

    @GET
    JsonObject metrics();

    @GET
    @Path("application")
    JsonObject applicationMetrics();
    
}