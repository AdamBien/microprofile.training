package airhacks.service.ping.boundary;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("posts")
public interface PostsResourceClient {

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    Response save(JsonObject post);
    
}