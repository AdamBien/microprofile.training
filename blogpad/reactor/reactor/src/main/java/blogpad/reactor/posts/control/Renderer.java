package blogpad.reactor.posts.control;

import org.graalvm.polyglot.Context;

public class Renderer {
 
    
    String render(String templateContent, String postContent) {
        try (Context context = Context.create("js")) {
            var bindings = context.getBindings("js");
            bindings.putMember("templateContent", templateContent);
            bindings.putMember("postContent", postContent);
            return context.eval("js", this.getRenderLogic()).asString();
        }
    }
    
    String getRenderLogic() {
        return """
            
                templateContent + postContent + 14
              
              """;

    }

}