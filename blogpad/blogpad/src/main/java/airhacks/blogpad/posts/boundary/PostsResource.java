package airhacks.blogpad.posts.boundary;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.eclipse.microprofile.metrics.annotation.Counted;

import airhacks.blogpad.posts.control.PostStore;
import airhacks.blogpad.posts.entity.Post;

@Path("posts")
public class PostsResource {

    @Inject
    PostStore store;

    @Counted
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Context UriInfo info, Post post) {
        Post postWithFileName = this.store.save(post);
        URI uri = info.getAbsolutePathBuilder().path(postWithFileName.fileName).build();
        return Response.created(uri).build();
    }

    @GET
    @Path("{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public Post find(@PathParam("title") String title) {
        return this.store.read(title);
    }
    
}