package com.mtrubs;

import com.mtrubs.kanban.repo.ConnectionFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.testng.annotations.AfterMethod;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;

import static org.testng.Assert.fail;

/**
 * @author mrubino
 * @since 2014-07-19
 */
public class MybatisTestHelper {

    private MybatisTestHelper() {
    }

    @AfterMethod
    public static void cleanDatabase() {
        SqlSessionFactory factory = ConnectionFactory.getSession();
        SqlSession session = factory.openSession();
        Connection connection = session.getConnection();
        ScriptRunner runner = new ScriptRunner(connection);
        runner.setLogWriter(null);

        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("database/clean.sql");
            runner.runScript(reader);
        } catch (IOException e) {
            fail(e.getMessage(), e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    fail(e.getMessage(), e);
                }
            }
            runner.closeConnection();
            session.close();
        }
    }
}
