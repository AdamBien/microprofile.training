package airhacks.blogpad.posts.boundary;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import airhacks.blogpad.posts.control.PostStore;
import airhacks.blogpad.posts.entity.Post;

@Path("posts")
public class PostsResource {

    @Inject
    PostStore store;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void save(Post post) {
        this.store.save(post);
    }

    @GET
    @Path("{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public Post find(@PathParam("title") String title) {
        return this.store.read(title);
    }
    
}