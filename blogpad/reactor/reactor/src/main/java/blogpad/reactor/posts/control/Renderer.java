package blogpad.reactor.posts.control;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.annotation.PostConstruct;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;

public class Renderer {

    Source handlebars;

    @PostConstruct
    public void init() {
        try {
			this.handlebars = Source.newBuilder("js", this.loadHandlebars(), "Handlebars").build();
        } catch (IOException e) {
            throw new IllegalStateException("cannot load handlebars",e);
        }
    }
 
    
    public String render(String templateContent, String postContent) {
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