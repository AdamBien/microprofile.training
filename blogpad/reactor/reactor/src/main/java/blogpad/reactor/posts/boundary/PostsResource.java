package blogpad.reactor.posts.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("posts")
public class PostsResource {

    @GET
    @Path("{title}")
    @Produces(MediaType.TEXT_HTML)
    public String findPost(@PathParam("title") String title){
        return "<h1>hello</h1> " + title;
    }


}


