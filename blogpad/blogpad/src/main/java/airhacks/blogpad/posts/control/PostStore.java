package airhacks.blogpad.posts.control;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import airhacks.blogpad.posts.entity.Post;

public class PostStore {


    void write(String fileName, String content) throws IOException {
        Path path = Path.of(fileName);
        Files.writeString(path, content);
    }
    
    String read(String fileName) throws IOException {
        Path path = Path.of(fileName);
        return Files.readString(path);
    }


    public String serialize(Post post) {
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.toJson(post);
    }
    
}