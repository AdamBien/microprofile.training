package airhacks.blogpad.posts.control;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import airhacks.blogpad.posts.entity.Post;

public class PostStoreTest {

    PostStore cut;

    @BeforeEach
    public void init() {
        this.cut = new PostStore();
    }
 
    @Test
    public void serializePost() {
        String stringified = this.cut.serialize(new Post("hello", "world"));
        assertNotNull(stringified);
        System.out.println("-> " + stringified);
    }

    @Test
    public void writeString() throws IOException {
        this.cut.write("target/nextPost", "hello, duke");
    }
}