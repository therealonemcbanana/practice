<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Read Animal-Food Relationships</title>
</head>
<body>
    <h1>Animal-Food Relationships</h1>
    <table border="1">
        <tr>
            <th>Food ID</th>
            <th>Animal ID</th>
        </tr>
        <c:forEach items="${animalFoods}" var="animalFood">
            <tr>
                <td>${animalFood.foodId}</td>
                <td>${animalFood.animalId}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="index.jsp">Back to Home</a>
</body>
</html>