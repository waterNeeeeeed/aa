<%--
    Document   : customer_add
    Created on : 2017-1-3, 23:49:16
    Author     : 帝
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form id="form" method="post" action="customer_create">
        <table>
        <tbody>
            <tr>
                <td>id:</td>
                <td><input type="text" name="id"></td>
            </tr>
            <tr>
                <td>username:</td>
                <td><input type="text" name="name"></td>
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
