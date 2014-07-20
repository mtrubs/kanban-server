package com.mtrubs.kanban.repo;

import com.mtrubs.MybatisTestHelper;
import com.mtrubs.kanban.domain.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Collection;

import static org.testng.Assert.*;

/**
 * @author mrubino
 * @since 2014-07-19
 */
@Test(groups = {"IntegrationTest"})
public class StoryRepositoryIntegrationTest {

    private StoryRepository repository;

    public StoryRepositoryIntegrationTest() {
        this.repository = new MybatisStoryRepository(ConnectionFactory.getSession());
    }

    @AfterMethod
    public void cleanDatabase() {
        MybatisTestHelper.cleanDatabase();
    }

    @Test
    public void getNonExistent() {
        Story story = this.repository.getStory(5);
        assertStory(story, null);
    }

    @Test
    public void createAndGet() {
        Story story = new Story("title", "description", 1);
        assertNull(story.getId());
        this.repository.create(story);
        assertNotNull(story.getId());
        Story get = this.repository.getStory(story.getId());
        assertStory(get, story);
    }

    @Test
    public void getAllEmpty() {
        Collection<Story> stories = this.repository.getAll();
        assertNotNull(stories);
        assertTrue(stories.isEmpty());
    }

    @Test
    public void createAndGetAll() {
        Story story1 = new Story("title", "description", 1);
        assertNull(story1.getId());
        this.repository.create(story1);
        assertNotNull(story1.getId());

        Story story2 = new Story("title", "description", 1);
        assertNull(story2.getId());
        this.repository.create(story2);
        assertNotNull(story2.getId());

        Collection<Story> results = this.repository.getAll();
        assertNotNull(results);
        assertEquals(results.size(), 2);

        for (Story actual : results) {
            if (story1.getId().equals(actual.getId())) {
                assertStory(actual, story1);
            } else if (story2.getId().equals(actual.getId())) {
                assertStory(actual, story2);
            } else {
                fail("unexpected story: " + actual.getId());
            }
        }
    }

    private static void assertStory(Story actual, Story expected) {
        if (expected == null) {
            assertNull(actual);
        } else {
            assertNotNull(actual);
            assertEquals(actual.getId(), expected.getId());
            assertEquals(actual.getTitle(), expected.getTitle());
            assertEquals(actual.getDescription(), expected.getDescription());
            assertEquals(actual.getPoints(), expected.getPoints());
        }
    }
}
