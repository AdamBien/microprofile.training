package airhacks.blogpad.posts.control;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import airhacks.blogpad.posts.entity.Post;

@Startup
@Singleton
public class Initialzer {


    static final String TITLE = "initial";
    
    @Inject
    PostStore store;

    @PostConstruct
    public void installFirstPost() {
        if(this.postExists())
            return;
        var post = this.createInitialPost();
        this.store.createNew(post);
    }
    
    Post createInitialPost() {
        return new Post(TITLE, "welcome to blogpad");
    }

    Post fetchPost() {
        return this.store.read(TITLE);
    }

    boolean postExists() {
        var post = this.fetchPost();
        if (post == null)
            return false;
        return TITLE.equalsIgnoreCase(post.title);
    }

    @Produces
    @Liveness
    public HealthCheck initialExists() {
        return () ->
        HealthCheckResponse.named("inital-post-exists").
                state(this.postExists()).
         build();
    }
    
}