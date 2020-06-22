package airhacks.blogpad.readiness.boundary;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

@Readiness
public class ReadinessCheck implements HealthCheck{

	@Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.up("blogpad");
    }
    
}