package airhacks.blogpad.posts.entity;

import java.time.LocalDateTime;

import javax.validation.constraints.Size;

public class Post {

    public String fileName;
    
    @Size(min=3,max=255)
    public String title;
    
    @Size(min=3)
    public String content;

    public LocalDateTime createdAt;
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