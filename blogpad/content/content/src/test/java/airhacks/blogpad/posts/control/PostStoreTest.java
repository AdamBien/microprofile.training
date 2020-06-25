package airhacks.blogpad.posts.control;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        this.cut.storageDir = "target";
        this.cut.init();
    }
 
    @Test
    public void serializePost() {
        String stringified = this.cut.serialize(new Post("hello", "world"));
        assertNotNull(stringified);
        System.out.println("-> " + stringified);
    }

    @Test
    public void writeString() throws IOException {
        String path = "nextPost";
        String expected = "hello, duke";
        this.cut.write(path, expected);
        String actual = this.cut.readString(path);
        assertEquals(expected, actual);
    }
    
    @Test
    public void savePostThenRead() throws IOException {
        String title = "first";
        Post expected = new Post(title, "hey,duke");
        this.cut.createNew(expected);
        
        Post actual = this.cut.read(title);
        assertEquals(expected.title,actual.title);
        assertEquals(expected.content,actual.content);
    }
}