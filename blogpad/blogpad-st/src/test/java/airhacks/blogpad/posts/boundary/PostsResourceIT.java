package airhacks.blogpad.posts.boundary;
import java.net.URI;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author airhacks.com
 */
public class PostsResourceIT {

    private PostsResourceClient client;

    @BeforeEach
    public void init() {
        URI uri = URI.create("http://localhost:9080/blogpad/resources/");
        this.client = RestClientBuilder.
                newBuilder().
                baseUri(uri).
                build(PostsResourceClient.class);

    }

    @Test
    public void save() {
        String title = "remote_hello";
        JsonObject post = Json.createObjectBuilder().add("title", title).add("content", "first st").build();
        Response response = this.client.save(post);
        int status = response.getStatus();
        assertEquals(204, status);

        response = this.client.findPost(title);
        status = response.getStatus();
        assertEquals(200, status);
    }

    @Test
    public void saveWithInvalidTitle() {
        String title = "/";
        JsonObject post = Json.createObjectBuilder().add("title", title).add("content", "first st").build();
        try{
            this.client.save(post);
            fail("Invalid title should not be stored");
        } catch (WebApplicationException ex) {
        
        }

    }
    
}