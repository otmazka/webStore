<%-- 
    Document   : newBook
    Created on : Nov 18, 2019, 7:21:40 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Новый телефон</title>
    </head>
    <body>
        <h1>Добавить новый телефон</h1>
        <form action="addProduct" method="POST">
            Бренд: <input type="text" name="title"><br><br>
            Модель: <input type="text" name="model"><br><br>
            Количество экземпляров: <input type="text" name="quantity"><br><br>
            Цена: <input type="text" name="price"><br><br>
            <input type="submit" value="Добавить телефон"><br><br>
        </form>
    </body>
</html>
