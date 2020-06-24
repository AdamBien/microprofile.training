package airhacks.blogpad.posts.control;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import airhacks.blogpad.posts.entity.Post;

@Startup
@Singleton
public class Initialzer {


    @Inject
    PostStore store;

    @PostConstruct
    public void installFirstPost() {
        Post post = this.createInitialPost();
        this.store.createNew(post);
    }
    
    Post createInitialPost() {
        return new Post("initial", "welcome to blogpad");
    }
    
}