<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Management</title>
</head>
<body>
<h1>Shopping Cart</h1>
<br/>
<table>
    <tr>
        <td>Hình ảnh</td>
        <td>Tên sản phẩm</td>
        <td>Giá</td>
        <td>Số lượng</td>
        <td>Thành tiền</td>
        <td>Sửa</td>
        <td>Xoá</td>
    </tr>
    <c:forEach items="${CartDetailList}" var="cart">
        <tr>
            <td><img src="${cart.getProduct().getImageURL()}" alt="img"></td>
            <td>${cart.getProduct().getName()}</td>
            <td>${cart.getProduct().getPrice()}</td>
            <td>${cart.getQuantity()}</td>
            <td>${cart.getQuantity() * cart.getProduct().getPrice()}</td>
<%--            <td><a href="${pageContext.request.contextPath}/shoppingCart?action=edit&id=${product.getId()}">Thêm vào giỏ hàng</a></td>--%>
            <td><a href="${pageContext.request.contextPath}/shoppingCart?action=remove&id=${cart.getProduct().getId()}">Xoá</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>