<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html lang="ru">
<head>
    <title>Meals</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="header">
    <h3 class="link"><a href="index.html">Home</a></h3>
    <hr>
</div>
<h2 class="text"> Meals </h2>

<p class="link"><a href="meals?action=create">Add meal</a></p>

<table class="table">
    <thead>
    <tr>
        <th>Date Time</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="listMeals" scope="request" type="java.util.List"/>
    <c:forEach items="${listMeals}" var="meal">
        <tr style="color:${meal.excess ? 'red' : 'green'}">
            <td><javatime:format value="${meal.dateTime}" style="MS"/></td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
            <td>
                <p class="link"><a href="meals?action=update&id=${meal.id}">Update</a>
            </td>
            <td>
                <p class="link"><a href="meals?action=delete&id=${meal.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
