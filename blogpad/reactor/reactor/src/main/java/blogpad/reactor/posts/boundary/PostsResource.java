package blogpad.reactor.posts.boundary;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.metrics.annotation.ConcurrentGauge;
import org.eclipse.microprofile.opentracing.Traced;

@Path("posts")
public class PostsResource {

    @Inject
    Reactor reactor;
   

    @GET
    @Traced
    @Fallback(fallbackMethod = "overloadedServer")
    @Bulkhead(2)
    @ConcurrentGauge
    @Path("{title}")
    @Produces(MediaType.TEXT_HTML)
    public Response findPost(@PathParam("title") String title){
        var content = this.reactor.render(title);
        return Response.ok(content).build();
    }


    public Response overloadedServer(String title) {
        return Response.status(503).
                header("reason", "overloaded server").
                header("affected_post", title).
        build();
    }
}


