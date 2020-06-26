package blogpad.reactor.posts.boundary;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import blogpad.reactor.posts.control.PostsResourceClient;

@Path("posts")
public class PostsResource {

    @Inject
    @RestClient
    PostsResourceClient client;

    @GET
    @Path("{title}")
    @Produces(MediaType.TEXT_HTML)
    public String findPost(@PathParam("title") String title){
        Response response = this.client.findPost("initial");
        return "<h1>hello</h1> " + title + " "+ response.readEntity(JsonObject.class);
    }


}


