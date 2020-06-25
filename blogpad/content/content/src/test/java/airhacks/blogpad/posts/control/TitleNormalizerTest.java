package airhacks.blogpad.posts.control;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TitleNormalizerTest {

    private TitleNormalizer cut;

	@BeforeEach
    public void init() {
        this.cut = new TitleNormalizer();
        this.cut.titleSeparator = "-";
        this.cut.init();
    }

    public static TitleNormalizer create() {
        var test = new TitleNormalizerTest();
        test.init();
        return test.cut;
    }

    @Test
    public void replaceInvalidCharacter() {
        var invalid = "hello%world";
        var expected = "hello-world";
        var actual = this.cut.normalize(invalid);
        assertEquals(expected, actual);
        
    }
    
}