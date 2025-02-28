<!DOCTYPE html>
<html>
<head>
    <title>Delete Animal-Employee Relationship</title>
</head>
<body>
    <h1>Delete Animal-Employee Relationship</h1>
    <form action="animalEmployee?action=delete" method="post">
        Animal ID: <input type="number" name="animalId"><br>
        Employee ID: <input type="number" name="employeeId"><br>
        <input type="submit" value="Delete">
    </form>
</body>
</html>