<%-- 
    Document   : showTakeOnBook
    Created on : Nov 25, 2019, 7:06:06 PM
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Покупка телефона</title>
    </head>
    
    <body>
        <p>${info}</p>
        <form action="buyProduct" method="POST">
        <h2>Список телефонов</h2>
        <select name="productId">
            <c:forEach var="product" items="${listProducts}" varStatus="status">
                <option value="${product.id}">
                    ${status.index+1}. ${product.title}. ${product.model}. ${product.price}. ${product.quantity}> </option>
                </c:forEach>
        </select>
        
        <h2>Список покупателей</h2>
         <select name="buyerId">
        <c:forEach var="buyer" items="${listBuyers}" varStatus="status">
        <option value="${buyer.id}">
            ${status.index+1}. ${buyer.name}. ${buyer.lastname}. ${buyer.money}. ${buyer.email}> 
        </option>
    </c:forEach>
         </select>
    <input type="submit" value="Купить телефон">
        </form>
</body>
</html>
