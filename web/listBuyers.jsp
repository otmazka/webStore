<%-- 
    Document   : listReaders
    Created on : Nov 25, 2019, 6:11:49 PM
    Author     : User
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список покупателей</title>
    </head>
    <body>
        <h1>Список покупателей</h1>
         <ul>
        <c:forEach var="buyer" items="${listBuyers}"> 
            <li>${buyer.name}. ${buyer.lastname}. ${buyer.email}</li>
            </c:forEach>

    </ul>

    </body>
</html>
