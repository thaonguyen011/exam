<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 22/12/2023
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Edit shopping cart</h1>
<form method="post">
  <p>Quantity</p>
  <label>
    <input type="number" name="newQuantity" value="${quantity}" placeholder="Enter number">
  </label>
  <input type="submit" value="Change">
</form>

</body>
</html>
