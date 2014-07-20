package com.mtrubs.kanban.repo;

import com.mtrubs.kanban.domain.Story;

import java.util.Collection;

/**
 * @author mrubino
 * @since 2014-07-14
 */
public interface StoryRepository {

    Story getStory(int id);

    Collection<Story> getAll();
    
    void insert(Story story);
}
