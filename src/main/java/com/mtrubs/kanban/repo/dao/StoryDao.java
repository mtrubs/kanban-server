package com.mtrubs.kanban.repo.dao;

import com.mtrubs.kanban.domain.Story;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;

/**
 * @author mrubino
 * @since 2014-07-14
 */
public interface StoryDao {

    @Select(" SELECT" +
            "   s.id as id," +
            "   s.title as title," +
            "   s.description as description," +
            "   s.points as points" +
            " FROM Story" +
            " WHERE id=#{storyId}")
    Story getStory(@Param("storyId") int id);

    @Select(" SELECT" +
            "   s.id as id," +
            "   s.title as title," +
            "   s.description as description," +
            "   s.points as points" +
            " FROM Story" +
            " ORDER BY s.id")
    Collection<Story> getAll();
}
