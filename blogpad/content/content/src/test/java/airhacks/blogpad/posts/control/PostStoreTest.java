package airhacks.blogpad.posts.control;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import airhacks.blogpad.posts.entity.Post;

public class PostStoreTest {

    PostStore cut;

    @BeforeEach
    public void init() {
        this.cut = new PostStore();
        this.cut.storageDir = "target";
        this.cut.normalizer = TitleNormalizerTest.create();
        Counter counter = mock(Counter.class);
        this.cut.registry = mock(MetricRegistry.class);
        when(this.cut.registry.counter(Mockito.anyString())).thenReturn(counter);
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