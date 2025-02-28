<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Read Animals</title>
</head>
<body>
    <h1>Animal List</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Gender</th>
            <th>Age</th>
            <th>Aviary ID</th>
            <th>Species ID</th>
        </tr>
        <c:forEach items="${animals}" var="animal">
            <tr>
                <td>${animal.id}</td>
                <td>${animal.name}</td>
                <td>${animal.gender}</td>
                <td>${animal.age}</td>
                <td>${animal.aviaryId}</td>
                <td>${animal.speciesId}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="index.jsp">Back to Home</a>
</body>
</html>