package blogpad.reactor.health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import blogpad.reactor.posts.control.PostsResourceClient;

@Liveness
@Readiness
@ApplicationScoped
public class HealthProbe implements HealthCheck {
    
    @Inject
    @RestClient
    PostsResourceClient client;

	@Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("content-availability").
                state(this.checkContentAvailability()).
        build();
    }
    
    boolean checkContentAvailability() {
      try{
        Response response = this.client.findPost("initial");
        return response.getStatus() == 200;
        } catch (Exception ex) {
            return false;
        }
    }
    
}