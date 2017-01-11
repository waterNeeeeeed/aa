<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" errorPage="error.jsp"%>
<%@ taglib prefix="mytag" uri="http://www.waterit.org/mytaglib" %>
<%@ page import="java.util.*"%>
<html>
<head>
    <title>客户管理</title>
</head>
<body>
<%
List<String> a = new ArrayList<String>();
a.add("hello");
a.add("gong");
a.add("happy");
pageContext.setAttribute("a", a);
%>
<table>
<mytag:helloWorld collection="a" item="item">
 <tr>
    <td>${pageScope.item}</td>
 </tr>
</mytag:helloWorld>
</table>
</body>
</html>