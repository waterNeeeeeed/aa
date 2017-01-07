<%@ page pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="org.water.chapter1.model.Customer" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Hello</title>
<%
List<Customer> cs = (List<Customer>)request.getAttribute("customerList");
%>
</head>
<body onload="list();">
<h1>Hello</h1>
<h2>CurrentTime:${currentTime}</h2>
<table id="cList" border="1">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>contact</th>
        <th>telephone</th>
        <th>email</th>
        <th>remark</th>
    </tr>
<%
for (int i=0; i<cs.size(); i++)
{
%>
    <tr>
        <td><%=cs.get(i).getId()%></td>
        <td><%=cs.get(i).getName()%></td>
        <td><%=cs.get(i).getContact()%></td>
        <td><%=cs.get(i).getTelephone()%></td>
        <td><%=cs.get(i).getEmail()%></td>
        <td><%=cs.get(i).getRemark()%></td>
    </tr>
<%
}
%>
</table>
<div id="size">
"${cs.size()}:"${cs.size()}<br/>
"<%=cs.size()%>"
</div>


<script type="text/javascript">

    var list = function()
    {
        var table = document.getElementById("cList");

            document.getElementById("size").innerHTML = "list";
            for(var i=0; i<${cs.size()}; i++)
            {
                var tr = table.insertRow(table.rows.length);
                var td = tr.insertCell(0);
                td.innerHTML = ${cs.get(i).getId()};
                td = tr.insertCell(1);
                td.innerHTML = ${cs.get(i).getName()};
                td = tr.insertCell(2);
                td.innerHTML = ${cs.get(i).getContact()};
                td = tr.insertCell(3);
                td.innerHTML = ${cs.get(i).getTelephone()};
                td = tr.insertCell(4);
                td.innerHTML = ${cs.get(i).getEmail()};
                td = tr.insertCell(5);
                td.innerHTML = ${cs.get(i).getRemark()};
            };
    }

</script>

</body>
</html>
