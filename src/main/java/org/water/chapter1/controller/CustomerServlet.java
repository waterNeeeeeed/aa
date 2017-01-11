package org.water.chapter1.controller;

//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.water.chapter1.model.Customer;
import org.water.chapter1.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 帝 on 2017/1/3.
 */
@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init(){
        customerService = new CustomerService();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        List<Customer> customerList = customerService.getCustomerList(null);
        req.setAttribute("customerList", customerList);
        req.getRequestDispatcher("/WEB-INF/jsp/customer.jsp").forward(req, resp);

    }
}
