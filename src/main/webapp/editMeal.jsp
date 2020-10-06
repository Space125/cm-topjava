<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Editing Meals</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<%--@elvariable id="meal" type="ru.javawebinar.topjava.model.Meal"--%>
<form action="meals" method="post">
    <table>
        <caption>
            <h2 class="text">${meal != null ? 'Edit Meal' : 'Create Meal'}</h2>
        </caption>
        <input type="hidden" name="id" value="${meal.id}">
        <tr>
            <th class="th">Date\Time</th>
            <td><input type="datetime-local" name="dateTime" value="${meal.dateTime}" required></td>
        </tr>
        <tr>
            <th class="th">Description</th>
            <td><input type="text" name="description" value="${meal.description}" required/></td>
        </tr>
        <tr>
            <th class="th">Calories</th>
            <td><input type="number" name="calories" value="${meal.calories}" required/></td>
        </tr>
    </table>
    <button type="submit">Save</button>
    <button onclick="window.history.back()" type="button">Cancel</button>
</form>
</body>
</html>
