<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Read Species</title>
</head>
<body>
    <h1>Species List</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Description</th>
        </tr>
        <c:forEach items="${speciesList}" var="species">
            <tr>
                <td>${species.id}</td>
                <td>${species.title}</td>
                <td>${species.description}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="index.jsp">Back to Home</a>
</body>
</html>