package airhacks.blogpad.posts.entity;

import java.time.LocalDateTime;

import javax.validation.constraints.Size;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class Post {

    @Schema(readOnly = true)
    public String fileName;
    
    @Schema(required = true,example = "openapi intro")
    @Size(min=3,max=255)
    public String title;
    
    @Size(min = 3)
    @Schema(required = true,example="how to use ...")
    public String content;

    @Schema(readOnly = true,type = SchemaType.STRING, format = "date-time")
    public LocalDateTime createdAt;
    @Schema(readOnly = true,type=SchemaType.STRING,format="date-time")
    public LocalDateTime modifiedAt;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post() {
    }

    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public void updateModifiedAt() {
        this.modifiedAt = LocalDateTime.now();
    }
    
}