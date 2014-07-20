package com.mtrubs.kanban.repo.dao;

import com.mtrubs.kanban.domain.Story;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
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
            " FROM Story s" +
            " WHERE id=#{storyId}")
    Story select(@Param("storyId") int id);

    @Select(" SELECT" +
            "   s.id as id," +
            "   s.title as title," +
            "   s.description as description," +
            "   s.points as points" +
            " FROM Story s" +
            " ORDER BY s.id")
    Collection<Story> selectAll();

    @Insert(" INSERT INTO Story (title, description, points)" +
            " VALUES (#{title}, #{description}, #{points})")
    @Options(useGeneratedKeys = true, flushCache = true)
    void insert(Story story);
}
