package airhacks.blogpad.posts.entity;

import javax.validation.constraints.Size;

public class Post {

    public String fileName;
    
    @Size(min=3,max=255)
    public String title;
    
    @Size(min=3)
    public String content;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post() {
    }

    
}