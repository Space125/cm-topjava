<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html lang="ru">
<head>
    <title>Editing Meals</title>
</head>
<body>
<%--@elvariable id="meal" type="ru.javawebinar.topjava.model.Meal"--%>
<form action="meals" method="post">
    <table class="table">
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
            <input type="hidden" name="id" value="${meal.id}">
        </c:if>
        <tr>
            <th align="left">Date\Time</th>
            <td>
                <input type="datetime-local" name="dateTime"
                       value="${meal.dateTime}" required>
            </td>
        </tr>
        <tr>
            <th align="left">Description</th>
            <td>
                <input type="text" name="description"
                       value="${meal.description}" required/>
            </td>
        </tr>
        <tr>
            <th align="left">Calories</th>
            <td>
                <input type="number" name="calories"
                       value="${meal.calories}" required/>
            </td>
        </tr>
    </table>
    <button type="submit">Save</button>
    <button onclick="window.history.back()" type="button">Cancel</button>
</form>
<%--<input type="submit" value="Save">--%>
<%--<a href="meals"> <input type="button" value="Cancel"></a>--%>
</body>
</html>
