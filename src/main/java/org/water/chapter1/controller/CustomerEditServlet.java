package org.water.chapter1.controller;

import org.water.chapter1.model.Customer;
import org.water.chapter1.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 帝 on 2017/1/3.
 */
@WebServlet("/customer_edit")
public class CustomerEditServlet extends HttpServlet{
    private CustomerService customerService;

    @Override
    public void init(){
        customerService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        long id = Long.parseLong(req.getParameter("id"));
        Customer cs = customerService.getCustomer(id);
        req.setAttribute("customer", cs);
        req.getRequestDispatcher("/WEB-INF/jsp/customer_edit.jsp").forward(req, resp);
    }
}
