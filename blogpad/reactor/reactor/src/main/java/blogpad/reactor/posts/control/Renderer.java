package blogpad.reactor.posts.control;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricRegistry.Type;
import org.eclipse.microprofile.metrics.annotation.RegistryType;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Source;

public class Renderer {

    Source handlebars;

    @Inject
    @RegistryType(type = Type.APPLICATION)
    MetricRegistry registry;

    @PostConstruct
    public void init() {
        try {
			this.handlebars = Source.newBuilder("js", this.loadHandlebars(), "Handlebars").build();
        } catch (IOException e) {
            throw new IllegalStateException("cannot load handlebars",e);
        }
    }
 
    public String render(String templateContent, String postContent) {
        try {
            return this.unsafeRender(templateContent, postContent);
        } catch (PolyglotException e) {
            this.registry.counter("rendering_errors").inc();
            throw new IllegalStateException("Rendering error, reason: " + e.getMessage(), ex);
        }
    }
    
    String unsafeRender(String templateContent, String postContent) {
        try (Context context = Context.create("js")) {
            var bindings = context.getBindings("js");
            context.eval(this.handlebars);
            bindings.putMember("templateContent", templateContent);
            bindings.putMember("postContent", postContent);
            return context.eval("js", this.getRenderLogic()).asString();
        }
    }
    
    String getRenderLogic() {
        return """
            const postAsJSON = JSON.parse(postContent);
            const compiledTemplate = Handlebars.compile(templateContent);
            compiledTemplate(postAsJSON);
            """;

    }

    Reader loadHandlebars() {
        var stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("js/handlebars-v4.7.6.js");
        return new InputStreamReader(stream);
    }


}