package com.mtrubs.kanban.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.mtrubs.kanban.repo.ConnectionFactory;
import com.mtrubs.kanban.repo.MybatisStoryRepository;
import com.mtrubs.kanban.repo.StoryRepository;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @author mrubino
 * @since 2014-07-13
 */
public class ResourceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(StoryRepository.class).to(MybatisStoryRepository.class).asEagerSingleton();
    }

    @Singleton
    @Provides
    SqlSessionFactory getSqlSessionFactory() {
        return ConnectionFactory.getSession();
    }
}
