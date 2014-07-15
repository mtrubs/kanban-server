package com.mtrubs.kanban.repo;

import com.google.inject.Inject;
import com.mtrubs.kanban.domain.Story;
import com.mtrubs.kanban.repo.dao.StoryDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Collection;

/**
 * @author mrubino
 * @since 2014-07-14
 */
public class MybatisStoryRepository implements StoryRepository {

    private final SqlSessionFactory connection;

    @Inject
    public MybatisStoryRepository(SqlSessionFactory connection) {
        this.connection = connection;
    }

    @Override
    public Story getStory(int id) {
        SqlSession session = this.connection.openSession();
        StoryDao dao = session.getMapper(StoryDao.class);
        Story story = dao.getStory(id);
        session.close();
        return story;
    }

    @Override
    public Collection<Story> getAll() {
        SqlSession session = this.connection.openSession();
        StoryDao dao = session.getMapper(StoryDao.class);
        Collection<Story> stories = dao.getAll();
        session.close();
        return stories;
    }
}
