package com.mtrubs.kanban.resource;

import com.google.inject.Inject;
import com.mtrubs.kanban.domain.Story;
import com.mtrubs.kanban.repo.StoryRepository;
import org.apache.http.HttpStatus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * @author mrubino
 * @since 2014-07-13
 */
@Path("/api/stories")
public class StoryResource extends BaseResource {

    private StoryRepository storyRepository;

    @Inject
    public StoryResource(ResourceSerializer serializer, StoryRepository storyRepository) {
        super(serializer);
        this.storyRepository = storyRepository;
    }

    @GET
    @Path("/")
    @Produces("application/json")
    public Response getStories() {
        Collection<Story> stories = this.storyRepository.getAll();
        return Response.status(HttpStatus.SC_OK).entity(toJson(stories)).build();
    }
}
