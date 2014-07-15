package com.mtrubs.kanban.repo;

import com.mtrubs.kanban.repo.dao.StoryDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @author mrubino
 * @since 2014-07-14
 */
public class ConnectionFactory {

    private static SqlSessionFactory SQL_MAPPER;

    static {
        InputStream stream = ConnectionFactory.class.getResourceAsStream("/configuration/mybatis-config.xml");
        SQL_MAPPER = new SqlSessionFactoryBuilder().build(stream);
        // TODO: might want to make this more dynamic
        SQL_MAPPER.getConfiguration().addMapper(StoryDao.class);
    }

    public static SqlSessionFactory getSession() {
        return SQL_MAPPER;
    }
}
