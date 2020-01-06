<%-- 
    Document   : listBooks
    Created on : Nov 25, 2019, 5:54:55 PM
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список телефонов</title>
    </head>

    <h1>Список телефонов</h1>
    <ul>
        <c:forEach var="product" items="${listProducts}"> 
            <li>${product.title}. ${product.model}. ${product.quantity}</li>
            </c:forEach>

    </ul>

</html>
