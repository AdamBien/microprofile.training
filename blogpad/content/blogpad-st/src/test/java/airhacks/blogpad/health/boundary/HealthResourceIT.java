package airhacks.blogpad.health.boundary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;

import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HealthResourceIT {
    
    
    private HealthResourceClient client;

	@BeforeEach
    public void init() {
        this.client = RestClientBuilder.newBuilder().baseUri(URI.create("http://localhost:8080/"))
                .build(HealthResourceClient.class);

    }
    
    @Test
    public void liveness() {
        Response response = this.client.liveness();
        assertEquals(200,response.getStatus());
        
    }
    @Test
    public void readiness() {
        Response response = this.client.readiness();
        assertEquals(200,response.getStatus());
        
    }

    
}