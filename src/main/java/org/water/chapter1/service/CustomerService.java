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

        Connection conn = null;
        try{
            //List<Customer> customerList = new ArrayList<Customer>();
            String sql = "SELECT * FROM customer";
            //conn = DBHelper.getConnection();//DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return DBHelper.queryEntityList(Customer.class,  sql);
            /*
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

            return customerList;*/
        //}catch (SQLException e){
           // LOGGER.error("execute sql failure", e);
        }finally {
            DBHelper.closeConnection();
            /*
            if (conn != null){
                try{
                    conn.close();
                }catch (SQLException e){
                    LOGGER.error("close connection failure", e);
                }
            }*/
        }
        //return null;
    }

    public Customer getCustomer(long id){
        return null;
    }

    public boolean createCustomer(Map<String, Object> fieldMap){
        return false;

    }

    public boolean updateCustomer(long id, Map<String, Object> fieldMap){

        return false;
    }

    public boolean deleteCustomer(long id){

        return false;
    }
}
