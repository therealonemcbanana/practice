<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Read Animal-Employee Relationships</title>
</head>
<body>
    <h1>Animal-Employee Relationships</h1>
    <table border="1">
        <tr>
            <th>Animal ID</th>
            <th>Employee ID</th>
        </tr>
        <c:forEach items="${animalEmployees}" var="animalEmployee">
            <tr>
                <td>${animalEmployee.animalId}</td>
                <td>${animalEmployee.employeeId}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="index.jsp">Back to Home</a>
</body>
</html>