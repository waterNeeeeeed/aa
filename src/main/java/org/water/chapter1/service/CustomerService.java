package org.water.chapter1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.water.chapter1.DBHelper;
import org.water.chapter1.model.Customer;
import org.water.chapter1.util.PropsUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by 帝 on 2017/1/1.
 */
public class CustomerService {
    private final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);


    public List<Customer> getCustomerList(String keyword){

        String sql = "SELECT * FROM customer";
        //conn = DBHelper.getConnection();//DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return DBHelper.queryEntityList(Customer.class,  sql);
        //Connection conn = null;
        /*try{
            //List<Customer> customerList = new ArrayList<Customer>();
            String sql = "SELECT * FROM customer";
            //conn = DBHelper.getConnection();//DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return DBHelper.queryEntityList(Customer.class,  sql);
            *//*
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setContact(rs.getString("contact"));
                customer.setTelephone(rs.getString("telephone"));
                customer.setEmail(rs.getString("email"));
                customer.setRemark(rs.getString("remark"));
                customerList.add(customer);
            }

            return customerList;*//*
        //}catch (SQLException e){
           // LOGGER.error("execute sql failure", e);
        }*/
        /*finally {
            DBHelper.closeConnection();
            *//*
            if (conn != null){
                try{
                    conn.close();
                }catch (SQLException e){
                    LOGGER.error("close connection failure", e);
                }
            }*//*
        }*/
        //return null;
    }

    public Customer getCustomer(long id){
        String sql = "SELECT * FROM customer where id = " + id;
        return DBHelper.queryEntity(Customer.class, sql);

        /*try{
            String sql = "SELECT * FROM customer where id = " + id;
            return DBHelper.queryEntity(Customer.class, sql);
        }finally {
            DBHelper.closeConnection();
        }*/

    }

    public boolean createCustomer(Map<String, Object> fieldMap){
        return DBHelper.insertEntity(Customer.class, fieldMap);

    }

    public boolean updateCustomer(long id, Map<String, Object> fieldMap){
        return DBHelper.updateEntity(Customer.class, id, fieldMap);
    }

    public boolean deleteCustomer(long id){
        return DBHelper.deleteEntity(Customer.class, id);
    }
}
