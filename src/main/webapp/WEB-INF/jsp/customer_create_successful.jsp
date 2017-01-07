<%@ page pageEncoding="UTF-8"%>
<html>
<head>
    <title>Customer Create</title>
</head>

<body>
${request.getParameter("name")}<br/>
<%=request.getParameter("name")%> Create Successfully!!!<br/>
<input type="submit" text="return" method="get" action="customer_create_success_return">
</body>
</html>