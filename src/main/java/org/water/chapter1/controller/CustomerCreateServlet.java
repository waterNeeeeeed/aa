package org.water.chapter1.controller;

import org.water.chapter1.model.Customer;
import org.water.chapter1.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 帝 on 2017/1/1.
 */
@WebServlet(name="customer_create",
            urlPatterns = {"/customer_create"})
public class CustomerCreateServlet extends HttpServlet{
    private CustomerService cs = new CustomerService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException{

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException{
        Customer customer = new Customer();
        customer.setName(req.getParameter("name"));
        customer.setContact(req.getParameter("contact"));
        customer.setTelephone(req.getParameter("telephone"));
        customer.setEmail(req.getParameter("email"));
        customer.setRemark(req.getParameter("remark"));

        Map<String, Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("name", req.getParameter("name"));
        fieldMap.put("contact", req.getParameter("contact"));
        fieldMap.put("telephone", req.getParameter("telephone"));
        fieldMap.put("email", req.getParameter("email"));
        fieldMap.put("remark", req.getParameter("remark"));
        cs.createCustomer(fieldMap);
        req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req, resp);
    }
}
