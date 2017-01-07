<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<h1>Hello</h1>
<h2>CurrentTime:${currentTime}</h2>
<h3>hot</h3>
<input type="submit" text="check">
<form id="form" method="post" action="customer_create">
<table>
<tbody>
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
<form action="customer_list" method="get" >
<input type="submit" value="列表" method="get" action="customer_list"><br/>
</form>

<div id="cs">
</div><br/>
<script type="text/javascript">
function as()
{
    document.getElementById("cs").innerHTML = "create successfully!";
};

</script>
<br/>
<input type="button" value="add" onclick="add();"><br/>
<br/>
<table id="cList" border="1">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>contact</th>
        <th>telephone</th>
        <th>email</th>
        <th>remark</th>
    </tr>
    <tr>
        <td>example</td>
        <td>example</td>
        <td>example</td>
        <td>example</td>
        <td>example</td>
        <td>example</td>
    </tr>
</table>


<script type="text/javascript">
var table = document.getElementById("cList");
function add()
{
    var tr = table.insertRow(table.rows.length);
    for (var i=0; i<6; i++)
    {
        var td = tr.insertCell(i);
        td.innerHTML = "Hello " + i;
    }
};
</script>
</body>
</html>
