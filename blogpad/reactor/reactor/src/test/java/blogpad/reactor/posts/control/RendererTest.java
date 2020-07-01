package blogpad.reactor.posts.control;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RendererTest {

    private Renderer cut;

	@BeforeEach
    public void init() {
        this.cut = new Renderer();
        this.cut.init();
    }
    
    @Test
    public void render() {
        var actual = this.cut.render("""
                <html>
                    <body>
                    <h1>{{title}}</h1>
                    <article>{{content}}</article>
                    </body>
                </html>
                """, """
                    {"title":"first post",
                    "content":"hello from ut"}
                """);
        System.out.println(" -- " + actual);
    }
}