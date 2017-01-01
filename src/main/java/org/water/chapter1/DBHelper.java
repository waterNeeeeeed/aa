package org.water.chapter1;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.water.chapter1.util.PropsUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Created by 帝 on 2017/1/1.
 */
public final class DBHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(DBHelper.class);
    private static final ThreadLocal<Connection> CONNECTION_HANDLER = new ThreadLocal<Connection>();

    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        Properties conf = PropsUtil.loadProps("config.properties");
        DRIVER = conf.getProperty("jdbc.driver");
        URL = conf.getProperty("jdbc.url");
        USERNAME = conf.getProperty("jdbc.username");
        PASSWORD = conf.getProperty("jdbc.password");
        try{
            Class.forName(DRIVER);
        }catch(ClassNotFoundException e){
            LOGGER.error("cant load jdbc driver", e);
        }
    }

    public static final QueryRunner QUERY_RUNNER = new QueryRunner();

    public static<T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params){
        List<T> entityList = null;
        try{
            Connection conn = getConnection();
            entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass), params);
        } catch (SQLException e) {
            LOGGER.error("query entity failure", e);
            throw new RuntimeException(e);
        }finally {
            closeConnection();
        }
        return entityList;
    }

    public static Connection getConnection(){
        Connection conn = CONNECTION_HANDLER.get();
        if (conn == null){
            try{
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                LOGGER.error("get connection failure", e);
                throw new RuntimeException(e);
            }finally {
                CONNECTION_HANDLER.set(conn);
            }

        }

        return conn;
    }

    public static void closeConnection(Connection conn){
        if (conn != null){
            try{
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure", e);
            }
        }
    }

    public static void closeConnection(){
        Connection conn = CONNECTION_HANDLER.get();
        if (conn != null){
            try{
                conn.close();
            }catch (SQLException e){
                LOGGER.error("close connection error", e);
                throw new RuntimeException(e);
            }finally {
                CONNECTION_HANDLER.remove();
            }
        }
    }
}
