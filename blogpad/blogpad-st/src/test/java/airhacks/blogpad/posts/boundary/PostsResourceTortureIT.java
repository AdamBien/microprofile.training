package airhacks.blogpad.posts.boundary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PostsResourceTortureIT {
    private PostsResourceClient client;
	private String title;
	private ExecutorService threadPool;

    @BeforeEach
    public void init() {
        URI uri = URI.create("http://localhost:8080/blogpad/resources/");
        this.client = RestClientBuilder.newBuilder().baseUri(uri).build(PostsResourceClient.class);
        this.title = "torture" + System.currentTimeMillis();
        JsonObject post = Json.createObjectBuilder().add("title", title).add("content", "for torture").build();
        Response response = this.client.createNew(post);
        int status = response.getStatus();
        assertEquals(201, status);
        this.threadPool = Executors.newFixedThreadPool(20);

    }
    

    @Test
    public void startTorture() {
        List<CompletableFuture<Void>> tasks = Stream
                .generate(this::runScenario).limit(500)
                .collect(Collectors.toList());
        tasks.forEach(CompletableFuture::join);
    }

    CompletableFuture<Void> runScenario() {
        return CompletableFuture.runAsync(this::findPost, this.threadPool).
        thenRunAsync(this::findNonExistingPost,
                this.threadPool);
    }
    
    void findNonExistingPost() {
        try {            
            this.client.findPost("not-existing" + System.nanoTime());
            fail("should not exist");
        } catch (WebApplicationException e) {
        }
    
    }

     void findPost() {
        Response response = this.client.findPost(this.title);
        JsonObject post = response.readEntity(JsonObject.class);
        assertNotNull(post);
    }
    
}