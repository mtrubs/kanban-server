package com.mtrubs.kanban.repo;

import com.mtrubs.MybatisTestHelper;
import com.mtrubs.ReflectionTestHelper;
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
        Story story = createStory("title", "description", 1);
        assertNull(getId(story));
        this.repository.create(story);
        assertNotNull(getId(story));
        Story get = this.repository.getStory(getId(story));
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
        Story story1 = createStory("title", "description", 1);
        assertNull(getId(story1));
        this.repository.create(story1);
        assertNotNull(getId(story1));

        Story story2 = createStory("title", "description", 1);
        assertNull(getId(story2));
        this.repository.create(story2);
        assertNotNull(getId(story2));

        Collection<Story> results = this.repository.getAll();
        assertNotNull(results);
        assertEquals(results.size(), 2);

        for (Story actual : results) {
            if (getId(story1).equals(getId(actual))) {
                assertStory(actual, story1);
            } else if (getId(story2).equals(getId(actual))) {
                assertStory(actual, story2);
            } else {
                fail("unexpected story: " + getId(actual));
            }
        }
    }

    private static Story createStory(String title, String description, int points) {
        Story story = new Story();
        ReflectionTestHelper.setField(story, "title", title);
        ReflectionTestHelper.setField(story, "description", description);
        ReflectionTestHelper.setField(story, "points", points);
        return story;
    }

    private static void assertStory(Story actual, Story expected) {
        if (expected == null) {
            assertNull(actual);
        } else {
            assertNotNull(actual);
            assertEquals(getId(actual), getId(expected));
            assertEquals(getTitle(actual), getTitle(expected));
            assertEquals(getDescription(actual), getDescription(expected));
            assertEquals(getPoints(actual), getPoints(expected));
        }
    }

    private static Integer getId(Story story) {
        return ReflectionTestHelper.getField(story, "id", Integer.class);
    }

    private static String getTitle(Story story) {
        return ReflectionTestHelper.getField(story, "title", String.class);
    }

    private static String getDescription(Story story) {
        return ReflectionTestHelper.getField(story, "description", String.class);
    }

    private static Integer getPoints(Story story) {
        return ReflectionTestHelper.getField(story, "points", Integer.class);
    }
}
