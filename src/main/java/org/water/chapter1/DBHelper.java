package org.water.chapter1;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.water.chapter1.util.CollectionUtil;
import org.water.chapter1.util.PropsUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public static<T> T queryEntity(Class<T> entityClass, String sql, Object... params){
        T entity = null;
        try{
            Connection conn = getConnection();
            entity = QUERY_RUNNER.query(conn, sql, new BeanHandler<T>(entityClass), params);
        } catch (SQLException e) {
            LOGGER.error("query entity failure", e);
            throw new RuntimeException();
        }finally {
            closeConnection();
        }
        return entity;
    }

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

    /*多表多列查询*/
    public static List<Map<String, Object>> executeQuery(String sql, Object... params){
        List<Map<String, Object>> result = null;
        try{
            Connection conn = getConnection();
            result = QUERY_RUNNER.query(conn, sql, new MapListHandler(), params);
        }catch (Exception e){
            LOGGER.error("execute query failure", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public static int executeUpdate(String sql, Object... params){
        int rows = 0;
        try{
            Connection conn = getConnection();
            rows = QUERY_RUNNER.update(conn, sql, params);
        } catch (SQLException e) {
            LOGGER.error("Update failure", e);
            throw new RuntimeException(e);
        }finally {
            closeConnection();
        }
        return rows;

    }

    /*
    * public static String  getTableName(Class<?> entityClass){
        return entityClass.getName().toLowerCase();
    }
    * */
    public static<T> String  getTableName(Class<T> entityClass){
        return entityClass.getSimpleName().toLowerCase();
    }

    /*
    * insert into table (col1, col2) values (?, ?)
    * fieldMap装了一行的所有要更新的列
    * */
    public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap){
        if (CollectionUtil.isEmpty(fieldMap)){
            LOGGER.error("cant insert entity:fieldMap is empty");
            return false;
        }

        String sql = "INSERT INTO " + getTableName(entityClass);
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");

        for(String fieldName: fieldMap.keySet()){
            columns.append(fieldName + ", ");
            values.append("?, ");
        }
        columns.replace(columns.lastIndexOf(","), columns.length(), ")");
        values.replace(values.lastIndexOf(","), values.length(), ")");
        sql += columns + "VALUES" + values;
        //
        Object[] params = fieldMap.values().toArray();
        return executeUpdate(sql, params) == 1;

    }

    /*
    * update tablename set s1=?, s2=? where id=?
    * */
    public static <T> boolean updateEntity(Class<T> entityClass, long id, Map<String, Object> fieldMap){
        if (CollectionUtil.isEmpty(fieldMap)){
            LOGGER.error("cant update entity:fieldMap is empty");
            return false;
        }
        String sql = "UPDATE " + getTableName(entityClass) + " SET ";
        StringBuilder columns = new StringBuilder();

        for(String fieldName:fieldMap.keySet()){
            columns.append(fieldName + "=?, ");
        }
        sql += columns.substring(0, columns.lastIndexOf(",")) + " WHERE id=?";

        List<Object> paramList = new ArrayList<Object>();
        paramList.addAll(fieldMap.values());
        paramList.add(id);
        Object[] params = paramList.toArray();

        return executeUpdate(sql, params) == 1;
    }

    public static <T> boolean deleteEntity(Class<T> entityClass, long id){
        String sql = "DELETE FROM " + getTableName(entityClass) + " WHERE id=?";
        return executeUpdate(sql, id) == 1;
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
