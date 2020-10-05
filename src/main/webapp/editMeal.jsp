<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html lang="ru">
<head>
    <title>Editing Meals</title>
</head>
<body>

<form action="meals" method="post">
    <table>
        <caption>
            <h2>
                <c:if test="${meal != null}">
                    Edit Meal
                </c:if>
                <c:if test="${meal == null}">
                    Create Meal
                </c:if>
            </h2>
        </caption>
        <c:if test="${meal != null}">
            <input type="hidden" name="id" value="<c:out value='${meal.id}'/>"/>
        </c:if>
        <tr>
            <th align="left">Date\Time</th>
            <td>
                <input type="datetime-local" name="dateTime" size="45"
                       value="${meal.dateTime}">
            </td>
        </tr>
        <tr>
            <th align="left">Description</th>
            <td>
                <input type="text" name="description" size="45"
                       value="<c:out value="${meal.description}"/> "/>
            </td>
        </tr>
        <tr>
            <th align="left">Calories</th>
            <td>
                <input type="number" name="calories" size="5"
                       value="<c:out value="${meal.calories}"/> "/>
            </td>
        </tr>

    </table>
</form>
<input type="submit" value="Save">
<a href="meals"> <input type="button" value="Cancel"></a>
</body>
</html>
