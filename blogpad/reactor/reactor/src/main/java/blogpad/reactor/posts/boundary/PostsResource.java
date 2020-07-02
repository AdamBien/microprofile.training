package blogpad.reactor.posts.boundary;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.annotation.ConcurrentGauge;
import org.eclipse.microprofile.opentracing.Traced;

@Path("posts")
public class PostsResource {

    @Inject
    Reactor reactor;
   

    @GET
    @Traced
    @ConcurrentGauge
    @Path("{title}")
    @Produces(MediaType.TEXT_HTML)
    public String findPost(@PathParam("title") String title){
        return this.reactor.render(title);
    }


}


