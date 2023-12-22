<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Management</title>
</head>
<body>
<h1>Product List</h1>
<br/>
<table>
    <tr>
        <td>Hình ảnh</td>
        <td>Tên sản phẩm</td>
        <td>Giá</td>
        <td></td>
    </tr>
    <c:forEach items="${productList}" var="product">
        <tr>
            <td><img src="${product.getImageURL()}" alt="img"></td>
            <td>${product.getName()}</td>
            <td>${product.getPrice()}</td>
            <td><a href="${pageContext.request.contextPath}/products?action=add&id=${product.getId()}">Thêm vào giỏ hàng</a></td>
        </tr>
    </c:forEach>
</table>

<p style="display: block;width: 500px;height: 500px">Chuyện người con gái Nam Xương là thiên truyện thứ 16 trong tổng số 20 truyện của Truyền kỳ mạn lục (So với 11 trong 20 truyện về phụ nữ). Truyền kỳ mạn lục (ghi chép tản mạn những chuyện kỳ lạ được lưu truyền) là <span style="display: inline-block"> tác phẩm được viết b</span>ằng chữ Hán.</p>
</body>
</html>