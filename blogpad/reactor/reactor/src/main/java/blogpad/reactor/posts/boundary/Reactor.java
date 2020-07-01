package blogpad.reactor.posts.boundary;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricRegistry.Type;
import org.eclipse.microprofile.metrics.annotation.RegistryType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import blogpad.reactor.posts.control.PostsResourceClient;
import blogpad.reactor.posts.control.Renderer;

public class Reactor {

    @Inject
    @RestClient
    PostsResourceClient client;

    @Inject
    @RegistryType(type = Type.APPLICATION)
    MetricRegistry registry;

    @Inject
    Renderer renderer;

    @Fallback(fallbackMethod = "getFallbackContent")
    public String render(String title) {
        Response response = this.client.findPost(title);
        var status = response.getStatus();
        registry.counter("content_find_post_status_" + status).inc();
        var postAsString = response.readEntity(String.class);
        return this.renderer.render(
        """
            <html>
            <body>
            <h1>{{title}}</h1>
            <article>{{content}}</article>
            </body>
            </html>
        """, postAsString);
    }
    

    public String getFallbackContent(String title) {
     return
     """
        <html>
            <body>
                <h1>Error :-( </h1>
                <article>Error rendering post with title: %s </article>
            </body>
        </html>
    """.formatted(title);
    }

    
}