<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html lang="ru">
<head>
    <title>Meals</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="header">
    <h3 class="link"><a href="index.html">Home</a></h3>
    <hr>
</div>
<h2 class="text"> Meals </h2>
<table class="table">
    <thead>
    <tr>
        <th>Date Time</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    </thead>

    <tbody>
    <jsp:useBean id="listMeals" scope="request" type="java.util.List"/>
    <c:forEach items="${listMeals}" var="meal">

        <c:set var="dateTime" value="${meal.dateTime}"/>
        <c:set var="description" value="${meal.description}"/>
        <c:set var="calories" value="${meal.calories}"/>
        <c:set var="excess" value="${meal.excess}"/>

        <c:choose>
            <c:when test="${excess eq false}">
                <tr style="color: red">
                    <td><javatime:format value="${dateTime}" style="MS"/></td>
                    <td><c:out value="${description}"/></td>
                    <td><c:out value="${calories}"/></td>
                </tr>
            </c:when>
            <c:otherwise>
                <tr style="color: green">
                    <td><javatime:format value="${dateTime}" style="MS"/></td>
                    <td><c:out value="${description}"/></td>
                    <td><c:out value="${calories}"/></td>
                </tr>
            </c:otherwise>
        </c:choose>

    </c:forEach>
    </tbody>

</table>
</body>
</html>
