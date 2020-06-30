package blogpad.reactor.posts.control;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RendererTest {

    private Renderer cut;

	@BeforeEach
    public void init() {
        this.cut = new Renderer();
    }
    
    @Test
    public void render() {
        var actual = this.cut.render("template", "input");
        System.out.println(" -- " + actual);
        assertEquals("duke42",actual);
    }
}