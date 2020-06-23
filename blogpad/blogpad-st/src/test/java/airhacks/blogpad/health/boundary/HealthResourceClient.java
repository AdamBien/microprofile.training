package airhacks.blogpad.health.boundary;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("health")
public interface HealthResourceClient {

    @GET
    @Path("live")
    public Response liveness();

    @GET
    @Path("ready")
    public Response readiness();
    
}