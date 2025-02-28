<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Read Aviaries</title>
</head>
<body>
    <h1>Aviary List</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Size</th>
            <th>State</th>
        </tr>
        <c:forEach items="${aviaries}" var="aviary">
            <tr>
                <td>${aviary.id}</td>
                <td>${aviary.size}</td>
                <td>${aviary.state}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="index.jsp">Back to Home</a>
</body>
</html>