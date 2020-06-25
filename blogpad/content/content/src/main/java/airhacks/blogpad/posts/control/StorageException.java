package airhacks.blogpad.posts.control;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class StorageException extends WebApplicationException {
    
    public StorageException(String message, Throwable cause) {
        super(Response.status(500).header("message", message).header("cause", cause.getMessage()).build());
    }    
}