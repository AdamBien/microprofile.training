package blogpad.reactor.posts.boundary;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricRegistry.Type;
import org.eclipse.microprofile.metrics.annotation.RegistryType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.graalvm.polyglot.Context;

import blogpad.reactor.posts.control.PostsResourceClient;

public class Reactor {

    @Inject
    @RestClient
    PostsResourceClient client;

    @Inject
    @RegistryType(type = Type.APPLICATION)
    MetricRegistry registry;

    public String render(String title) {
        Response response = this.client.findPost(title);
        var status = response.getStatus();
        registry.counter("content_find_post_status_" + status).inc();
        return "rendered " + response.readEntity(JsonObject.class);
    }

    String render(String template, String input) {
        return null;
    }
    
}