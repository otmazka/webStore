<%-- 
    Document   : listHistories
    Created on : Jan 6, 2020, 6:49:59 PM
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список проданных телефонов</title>
    </head>
    <body>
        <h1>Список проданных телефонов</h1>
        <ul>
        <c:forEach var="history" items="${listHistories}"> 
            <li>${history.product.title}. ${history.product.model}. ${history.product.price}. ${history.product.quantity}. ${history.product.count}</li>
            </c:forEach>

    </ul>
    </body>
</html>
