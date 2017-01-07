package org.water.chapter1.controller;

import org.water.chapter1.model.Customer;
import org.water.chapter1.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by hasee-pc on 2017/1/6.
 */
@WebServlet(name="customer_list",
            urlPatterns="/customer_list")
public class CustomerListServlet extends HttpServlet{
   // public List<Customer> customerList = null;
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException{
        req.setAttribute("customerList", new CustomerService().getCustomerList(null));
        req.getRequestDispatcher("/WEB-INF/jsp/customer_list.jsp").forward(req, resp);
    }
}
