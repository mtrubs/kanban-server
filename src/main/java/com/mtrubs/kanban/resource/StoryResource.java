package com.mtrubs.kanban.resource;

import com.google.inject.Inject;
import com.mtrubs.kanban.domain.Story;
import com.mtrubs.kanban.repo.StoryRepository;
import org.apache.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * Resource for interacting with stories.
 *
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

    /**
     * Responsible for getting all the stories.
     *
     * @return a list of all stories in json.
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStories() {
        Collection<Story> stories = this.storyRepository.getAll();
        return Response.status(HttpStatus.SC_OK).entity(toJson(stories)).build();
    }

    /**
     * Responsible for creating a new story.
     *
     * @param json the string json.
     * @return the created story in json.
     */
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStory(String json) {
        Story story = fromJson(json, Story.class);
        this.storyRepository.create(story);
        return Response.status(HttpStatus.SC_CREATED).entity(toJson(story)).build();
    }

    /**
     * Responsible for getting the given story.
     *
     * @param id the id of the story to get.
     * @return the story in json.
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStory(@PathParam("id") int id) {
        Story story = this.storyRepository.getStory(id);
        if (story == null) {
            return Response.status(HttpStatus.SC_NOT_FOUND).build();
        } else {
            return Response.status(HttpStatus.SC_OK).entity(toJson(story)).build();
        }
    }
}
