<%-- 
    Document   : customer_edit
    Created on : 2017-1-3, 23:49:16
    Author     : 帝
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.water.chapter1.model.Customer"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
            String id = request.getParameter("id");
            Customer cs = (Customer)request.getAttribute("customer");

        %>
    </head>
    <body>
        <h1>Hello World!</h1>
        <div>
        <%=cs.getName()%>
        </div>
        <form id="form" method="post" action="customer_create">
        <table style="text-align:right">
        <tbody>
            <tr>
                <td>id:</td>
                <td><input type="text" name="id" value="${param['id']}"></td>
            </tr>
            <tr>
                <td>username:</td>
                <td><input type="text" name="name" value="${cs.name}"></td>
            </tr>
            <tr>
                <td>contact:</td>
                <td><input type="text" name="contact"></td>
            </tr>
            <tr>
                <td>telephone:</td>
                <td><input type="text" name="telephone"></td>
            </tr>
            <tr>
                <td>email:</td>
                <td><input type="text" name="email"></td>
            </tr>
            <tr>
                <td>remark:</td>
                <td><input type="text" name="remark"></td>
            </tr>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="3" style="text-align:right"><input type="submit" value="提交" ></td>
            </tr>
        </tfoot>
        </table>
        </form>
    </body>
</html>
