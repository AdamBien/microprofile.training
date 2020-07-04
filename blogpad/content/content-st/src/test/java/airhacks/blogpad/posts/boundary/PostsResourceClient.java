package airhacks.blogpad.posts.boundary;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;

@Path("posts")
public interface PostsResourceClient {

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    Response update(JsonObject post);

    @POST
    @ClientHeaderParam(name = "Authorization", value = "{tokenWithAuthorRole}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response createNew(JsonObject post);

    @GET
    @Path("{title}")
    @Produces(MediaType.APPLICATION_JSON)
    Response findPost(@PathParam("title") String title);

    default String tokenWithAuthorRole() {
        return "Bearer eyJraWQiOiJqd3Qua2V5IiwidHlwIjoiSldUIiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJkdWtlIiwidXBuIjoiZHVrZSIsImF1dGhfdGltZSI6MTU5Mzg0MzIzMywiaXNzIjoiYWlyaGFja3MiLCJncm91cHMiOlsic3Vic2NyaWJlciIsImF1dGhvciJdLCJleHAiOjE1OTM4NDQyMzMsImlhdCI6MTU5Mzg0MzIzMywianRpIjoiNDIifQ.PENOvtDot_br9K55qBpMowH730X1N7SOuUfPDxyg8NptiC9shA0JMe1y_XzOaqQEDBYzztCgq6p8RMiTk13SxJ9g_ZZYKGxZXjqoiz5mh5RI786caayqLKzgq-LjnOpbeB1zGX2FYhUu5jzlP92rQr27fgG0vdQIKScz7fDObWnynOGIuRk5ZE5ldeEvGvWC_vNx5tDsIUwVZt8SZxM8sjlPJj-CcfCScHTwrsn-073NcSOcv0qhFsb62lkdOfC5V5ksfSbYpGPh7SxEn5VC8oQo3NtInJUULcg3vCpiEOaNsAOU-oBgky6CVYnQEInrqAKilmBDo5NgmEM-c1OXeA";
    }
    
}