package airhacks.blogpad.metrics.boundary;
import java.net.URI;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import airhacks.blogpad.posts.boundary.PostsResourceIT;

/**
 *
 * @author airhacks.com
 */
public class MetricsResourceIT {

    private MetricsResourceClient client;

    @BeforeAll
    public static void initMetricsWithBusinessCall() {
        var test = new PostsResourceIT();
        test.init();
        test.createNew();
    }

    @BeforeEach
    public void init() {
        URI uri = URI.create("http://localhost:8080/");
        this.client = RestClientBuilder.
                newBuilder().
                baseUri(uri).
                build(MetricsResourceClient.class);

    }

    @Test
    public void metrics() {
        var metrics = this.client.metrics();
        assertNotNull(metrics);
        assertFalse(metrics.isEmpty());
    }

    @Test 
    public void applicationMetrics() {
        var metrics = this.client.applicationMetrics();
        assertNotNull(metrics);
        assertFalse(metrics.isEmpty());
        System.out.println("metrics from server: " + metrics);
        int saveInvocationCounter = metrics.getJsonNumber("airhacks.blogpad.posts.boundary.PostsResource.createNew")
                .intValue();
        assertTrue(saveInvocationCounter >= 0);

    }
   
}