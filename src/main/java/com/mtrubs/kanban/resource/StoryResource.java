package com.mtrubs.kanban.resource;

import com.google.inject.Inject;
import org.apache.http.HttpStatus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * @author mrubino
 * @since 2014-07-13
 */
@Path("/api/stories")
public class StoryResource extends BaseResource {

    @Inject
    public StoryResource(ResourceSerializer serializer) {
        super(serializer);
    }

    @GET
    @Path("/")
    @Produces("application/json")
    public Response getStories() {
        // TODO: MTR
        return Response.status(HttpStatus.SC_OK).entity(toJson("")).build();
    }
}
