package blogpad.reactor.posts.control;

import org.graalvm.polyglot.Context;

public class Renderer {
 
    
    String render(String template, String input) {
        try (Context context = Context.create("js")) {
            var bindings = context.getBindings("js");
            bindings.putMember("message", "duke");
            return context.eval("js","message + 42").asString();
        }
    }

}