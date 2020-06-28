package airhacks.blogpad.posts.boundary;
import java.net.URI;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import airhacks.blogpad.Configuration;

/**
 *
 * @author airhacks.com
 */
public class PostsResourceIT {

    private PostsResourceClient client;

    @BeforeEach
    public void init() {
        var uri = Configuration.getValue("resource.uri");
        this.client = RestClientBuilder.
                newBuilder().
                baseUri(URI.create(uri)).
                build(PostsResourceClient.class);

    }

    @Test
    public void createNew() {
        String title = "remote_hello" + System.currentTimeMillis();
        JsonObject post = Json.createObjectBuilder().add("title", title).add("content", "first st").build();
        Response response = this.client.createNew(post);
        int status = response.getStatus();
        assertEquals(201, status);

        response = this.client.findPost(title);
        status = response.getStatus();
        assertEquals(200, status);
        var fetchedTitle = response.readEntity(JsonObject.class);
        System.out.println("--- " + fetchedTitle);
        assertNotNull(fetchedTitle.getString("createdAt", null));
        assertNull(fetchedTitle.getString("modifiedAt",null));
    }

    @Test
    public void saveTitleWithInvalidFileName() {
        String title = "hello/world" + System.currentTimeMillis();
        JsonObject post = Json.createObjectBuilder().add("title", title).add("content", "first st").build();
        Response creationResponse = this.client.createNew(post);
        assertEquals(201,creationResponse.getStatus());

        Response response = this.client.findPost(title.replace("/","-"));
        int status = response.getStatus();
        assertEquals(200, status);
    }

    @Test
    public void fetchNonExistingTitle() {
        var title = "ne" + System.nanoTime();
        Response response = this.client.findPost(title);
        int status = response.getStatus();
        assertEquals(204, status);
    }

    @Test
    public void fetchExistingTitle() {
        var title = "initial";
        Response response = this.client.findPost(title);
        int status = response.getStatus();
        assertEquals(200, status);
        var post = response.readEntity(JsonObject.class);
        assertTrue(title.equalsIgnoreCase(post.getString("title")));
    }
    

    @Test
    public void saveWithTooShortTitle() {
        String title = "no";
        JsonObject post = Json.createObjectBuilder().add("title", title).add("content", "first st").build();
        try{
            this.client.createNew(post);
            fail("Expecting WebApplicationEception with 400");
        } catch (WebApplicationException ex) {
            var response = ex.getResponse();
            var status = response.getStatus();
            assertEquals(400,status);
        }
    }
    
}