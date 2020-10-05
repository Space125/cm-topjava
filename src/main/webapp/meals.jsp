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

<p class="link"><a href="${pageContext.request.contextPath}/meals?action=create">Add meal</a></p>

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

        <c:set var="dateTime" value="${meal.dateTime}"/>
        <c:set var="description" value="${meal.description}"/>
        <c:set var="calories" value="${meal.calories}"/>
        <c:set var="excess" value="${meal.excess}"/>

        <c:choose>
            <c:when test="${excess eq true}">
                <tr>
                    <td style="color: red"><javatime:format value="${dateTime}" style="MS"/></td>
                    <td style="color: red"><c:out value="${description}"/></td>
                    <td style="color: red"><c:out value="${calories}"/></td>
                    <td>
                        <p class="link"><a
                                href="${pageContext.request.contextPath}/meals?action=update&id=<c:out value="${meal.id}" /> ">Update</a>
                        </p>
                    </td>
                    <td>
                        <p class="link"><a
                                href="${pageContext.request.contextPath}/meals?action=delete&id=<c:out value="${meal.id}" /> ">Delete</a>
                        </p>
                    </td>
                </tr>
            </c:when>
            <c:otherwise>
                <tr>
                    <td style="color: green"><javatime:format value="${dateTime}" style="MS"/></td>
                    <td style="color: green"><c:out value="${description}"/></td>
                    <td style="color: green"><c:out value="${calories}"/></td>
                    <td>
                        <p class="link"><a
                                href="${pageContext.request.contextPath}/meals?action=update&id=<c:out value="${meal.id}" /> ">Update</a>
                        </p>
                    </td>
                    <td>
                        <p class="link"><a
                                href="${pageContext.request.contextPath}/meals?action=delete&id=<c:out value="${meal.id}" /> ">Delete</a>
                        </p>
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>

    </c:forEach>
    </tbody>

</table>
</body>
</html>
