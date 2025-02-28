<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Read Foods</title>
</head>
<body>
    <h1>Food List</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Amount</th>
        </tr>
        <c:forEach items="${foods}" var="food">
            <tr>
                <td>${food.id}</td>
                <td>${food.name}</td>
                <td>${food.amount}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="index.jsp">Back to Home</a>
</body>
</html>