package airhacks.blogpad.posts.boundary;

import java.net.URI;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import airhacks.blogpad.posts.control.PostStore;
import airhacks.blogpad.posts.entity.Post;

@Path("posts")
public class PostsResource {

    @Inject
    PostStore store;

    @Counted
    @POST
    @APIResponse(
        responseCode = "400",
        description = "Post with the title already exists. Use PUT for updates"
    )
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNew(@Context UriInfo info, @Valid Post post) {
        Post postWithFileName = this.store.createNew(post);
        URI uri = info.getAbsolutePathBuilder().path(postWithFileName.fileName).build();
        return Response.created(uri).build();
    }

    @Counted
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Context UriInfo info, @Valid Post post) {
        this.store.update(post);
        return Response.ok().build();
    }



    @Timed
    @GET
    @Path("{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public Post find(@PathParam("title") String title) {
        return this.store.read(title);
    }
    
}