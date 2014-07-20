package com.mtrubs.kanban.repo;

import com.mtrubs.kanban.domain.Story;
import com.mtrubs.MybatisBaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author mrubino
 * @since 2014-07-19
 */
@Test(groups = {"IntegrationTest"})
public class StoryRepositoryIntegrationTest extends MybatisBaseTest {

    private StoryRepository repository;

    public StoryRepositoryIntegrationTest() {
        this.repository = new MybatisStoryRepository(ConnectionFactory.getSession());
    }

    @Test
    public void selectNonExistent() {
        // story does not exist
        Story story = this.repository.getStory(-1);
        assertNull(story);
    }

    @Test
    public void insertAndSelect() {
        Story story = new Story("title", "description", 1);
        assertNull(story.getId());
        this.repository.insert(story);
        assertNotNull(story.getId());
        Story get = this.repository.getStory(story.getId());
        assertNotNull(get);
        assertEquals(get.getId(), story.getId());
        assertEquals(get.getTitle(), story.getTitle());
        assertEquals(get.getDescription(), story.getDescription());
        assertEquals(get.getPoints(), story.getPoints());
    }
}
