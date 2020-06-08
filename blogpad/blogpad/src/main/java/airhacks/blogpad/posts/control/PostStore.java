package airhacks.blogpad.posts.control;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import airhacks.blogpad.posts.entity.Post;

public class PostStore {


    public String serialize(Post post) {
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.toJson(post);
    }
    
}