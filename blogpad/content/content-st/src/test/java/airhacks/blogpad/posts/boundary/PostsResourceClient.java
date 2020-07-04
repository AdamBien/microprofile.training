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
        return "Bearer eyJraWQiOiJqd3Qua2V5IiwidHlwIjoiSldUIiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJkdWtlIiwidXBuIjoiZHVrZSIsImF1dGhfdGltZSI6MTU5Mzg2NTY4OCwiaXNzIjoiYWlyaGFja3MiLCJncm91cHMiOlsic3Vic2NyaWJlciIsImF1dGhvciJdLCJleHAiOjI1OTM4NDQyMzMsImlhdCI6MTU5Mzg2NTY4OCwianRpIjoiNDIifQ.f0U4GRhhsHAWUs05aTSlUFjP6aRne2et1inBP7C2B67IfNQBR9H35TPvy0uPhaCY8ohMAsd8xTs7seavjIqHDYgDmv8sm9RyNwFei2pWSd97-sWB0FoaRqFcVxDzM5dCKPN_r5GJpigHdlpsiyYctTEDWAK325zTf-1MRghTo57Cqy1xoAG5bUcRs-0ijmSr-uZ4ChBbJj_W3eSt9IH9fPOKClyxEQzm6Ao2xLOQj3KfFBYTA1qF_X7sPyVcIa3A249kG7XBP4-ZJzTJ3cwEM6OSQ78stn6zaass6i28WJQgl-Xdig64MzD3-y7SteK9NLb_HVOoVQisM8jSJCh-Ng";
    }
    
}