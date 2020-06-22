package airhacks.blogpad.posts.control;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.BadRequestException;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import airhacks.blogpad.posts.entity.Post;

public class PostStore {

    @Inject
    @ConfigProperty(name = "root.storage.dir")
    String storageDir;

    @Inject
    @ConfigProperty(name="minimum.storage.space",defaultValue = "50")
    int storageThreshold;

    @Inject
    TitleNormalizer normalizer;

	Path storageDirectoryPath;

    @PostConstruct
    public void init() {
        this.storageDirectoryPath = Path.of(this.storageDir);
    }

    @Produces
    @Liveness
    public HealthCheck checkPostsDirectoryExists() {
        return () -> HealthCheckResponse.
        named("posts-directory-exists").
                state(Files.exists(this.storageDirectoryPath)).
                build();
    }
    @Produces
    @Liveness
    public HealthCheck checkEnoughSpace() {
        var size = this.getPostsStorageSpaceInMB();
        var enoughSpace = size >= this.storageThreshold;
        return () -> HealthCheckResponse.named("posts-directory-has-space")
                .state(enoughSpace).
                build();
    }
    
    long getPostsStorageSpaceInMB() {
        try {
			return Files.getFileStore(this.storageDirectoryPath).getUsableSpace() / 1024 / 1024;
        } catch (IOException e) {
            throw new StorageException("Cannot fetch size information from " + this.storageDirectoryPath,e);
        }

    }

    public Post createNew(Post post) {
        var fileName = this.normalizer.normalize(post.title);
        if (this.fileExists(fileName)) {
            throw new BadRequestException("Post with name: " + fileName + " already exists, use PUT for updates");
        }
        post.setCreatedAt();
        post.fileName = fileName;
        var stringified = serialize(post);
        try {
            write(fileName, stringified);
            return post;
        } catch (IOException ex) {
            throw new StorageException("Cannot save post " + fileName, ex);
        }
    }

    boolean fileExists(String fileName) {
        Path fqn = this.storageDirectoryPath.resolve(fileName);
        return Files.exists(fqn);
    }

    public void update(Post post) {
        var fileName = this.normalizer.normalize(post.title);
        if (!this.fileExists(fileName)) {
            throw new BadRequestException("Post with name: " + fileName + " does not exists, use POST to create");
        }
        post.updateModifiedAt();
        var stringified = serialize(post);
        try {
            write(fileName, stringified);
        } catch (IOException ex) {
            throw new StorageException("Cannot save post " + fileName, ex);
        }
    }
    
    
    String serialize(Post post) {
        var jsonb = JsonbBuilder.create();
        return jsonb.toJson(post);
    }


    void write(String fileName, String content) throws IOException {
        var path = this.storageDirectoryPath.resolve(fileName);
        Files.writeString(path, content);
    }


    public Post read(String title) {
        var fileName = this.normalizer.normalize(title);
        try{
        var stringified = this.readString(fileName);
        return this.deserialize(stringified);
    } catch (IOException ex) {
        throw new StorageException("Cannot fetch post: " + fileName,ex);
    }
    }

    Post deserialize(String stringified){
        var jsonb = JsonbBuilder.create();
        return jsonb.fromJson(stringified, Post.class);
    }
 

    String readString(String fileName) throws IOException {
        var path = this.storageDirectoryPath.resolve(fileName);
        return Files.readString(path);
    }


    
}